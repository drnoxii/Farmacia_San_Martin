package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.*;
import com.sanmartin.farmacias.Entity.*;
import com.sanmartin.farmacias.Repository.CajaRepository;
import com.sanmartin.farmacias.Repository.MovimientoCajaRepository;
import com.sanmartin.farmacias.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CajaServicesImpl implements  ICajaServices{

    private final CajaRepository cajaRepository;
    private final MovimientoCajaRepository movimientoCajaRepository;
    private final UsuarioRepository usuarioRepository;

    public CajaServicesImpl(CajaRepository cajaRepository, MovimientoCajaRepository movimientoCajaRepository, UsuarioRepository usuarioRepository) {
        this.cajaRepository = cajaRepository;
        this.movimientoCajaRepository = movimientoCajaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public CajaResponseDTO abrirCaja(CajaRequestDTO dto) {


        cajaRepository.findByUsuarioIdUsuarioAndEstado(dto.idUsuario(), EstadoCaja.ABIERTA)
                .ifPresent(c -> {
                    throw new RuntimeException("El usuario ya tiene una caja abierta");
                });


        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        Caja caja = new Caja();
        caja.setUsuario(usuario);
        caja.setMontoInicial(dto.montoInicial());
        caja.setEstado(EstadoCaja.ABIERTA);
        caja.setFechaApertura(LocalDateTime.now());

        caja = cajaRepository.save(caja);
        return toResponse(caja);
    }


    @Override
    @Transactional
    public CajaResponseDTO cerrarCaja(Long idCaja, CajaCierreRequestDTO dto) {


        Caja caja = cajaRepository.findById(idCaja)
                .orElseThrow(() -> new RuntimeException("Caja no encontrada"));


        if (caja.getEstado() == EstadoCaja.CERRADA) {
            throw new RuntimeException("La caja ya está cerrada");
        }


        BigDecimal ingresos = movimientoCajaRepository.sumMontoByCajaAndTipos(
                idCaja,
                List.of(TipoMovimientoCaja.INGRESO, TipoMovimientoCaja.VENTA)
        );

        BigDecimal egresos = movimientoCajaRepository.sumMontoByCajaAndTipos(
                idCaja,
                List.of(TipoMovimientoCaja.EGRESO, TipoMovimientoCaja.DEVOLUCION)
        );


        BigDecimal esperado = caja.getMontoInicial()
                .add(ingresos)
                .subtract(egresos);

        BigDecimal diferencia = dto.montoContado().subtract(esperado);


        caja.setMontoFinal(dto.montoContado());
        caja.setDiferencia(diferencia);
        caja.setFechaCierre(LocalDateTime.now());
        caja.setEstado(EstadoCaja.CERRADA);

        caja = cajaRepository.save(caja);
        return toResponse(caja);
    }

    @Override
    public CajaResponseDTO obtenerPorId(Long idCaja) {
        Caja caja = cajaRepository.findById(idCaja)
                .orElseThrow(() -> new RuntimeException("Caja no encontrada"));
        return toResponse(caja);
    }

    @Override
    public List<CajaResponseDTO> listar() {
        return cajaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<CajaResponseDTO> listarAbiertas() {
        return cajaRepository.findByEstado(EstadoCaja.ABIERTA).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<CajaResponseDTO> listarPorUsuario(Long idUsuario) {
        return cajaRepository.findByUsuarioIdUsuario(idUsuario).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<CajaResponseDTO> listarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        return cajaRepository.findByFechaAperturaBetween(desde, hasta).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public MovimientoCajaResponseDTO registrarMovimiento(MovimientoCajaRequestDTO dto) {


        Caja caja = cajaRepository.findById(dto.idCaja())
                .orElseThrow(() -> new RuntimeException("Caja no encontrada"));

        if (caja.getEstado() == EstadoCaja.CERRADA) {
            throw new RuntimeException("No se puede registrar movimientos en una caja cerrada");
        }


        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        MovimientoCaja mov = new MovimientoCaja();
        mov.setCaja(caja);
        mov.setUsuario(usuario);
        mov.setTipo(dto.tipo());
        mov.setMonto(dto.monto());
        mov.setDescripcion(dto.descripcion());
        mov.setFecha(LocalDateTime.now());

        mov = movimientoCajaRepository.save(mov);
        return toResponse(mov);
    }

    @Override
    public List<MovimientoCajaResponseDTO> listarMovimientos(Long idCaja) {

        if (!cajaRepository.existsById(idCaja)) {
            throw new RuntimeException("Caja no encontrada");
        }

        return movimientoCajaRepository.findByCajaIdCaja(idCaja).stream()
                .map(this::toResponse)
                .toList();
    }

    private CajaResponseDTO toResponse(Caja caja) {

        BigDecimal ingresos = movimientoCajaRepository.sumMontoByCajaAndTipos(
                caja.getIdCaja(),
                List.of(TipoMovimientoCaja.INGRESO, TipoMovimientoCaja.VENTA)
        );

        BigDecimal egresos = movimientoCajaRepository.sumMontoByCajaAndTipos(
                caja.getIdCaja(),
                List.of(TipoMovimientoCaja.EGRESO, TipoMovimientoCaja.DEVOLUCION)
        );

        BigDecimal esperado = caja.getMontoInicial()
                .add(ingresos)
                .subtract(egresos);

        return new CajaResponseDTO(
                caja.getIdCaja(),
                caja.getUsuario().getIdUsuario(),
                caja.getUsuario().getPersona().getNombre(),
                caja.getMontoInicial(),
                caja.getMontoFinal(),
                esperado,
                caja.getDiferencia(),
                caja.getEstado().name(),
                caja.getFechaApertura(),
                caja.getFechaCierre(),
                ingresos,
                egresos
        );
    }

    private MovimientoCajaResponseDTO toResponse(MovimientoCaja mov) {
        return new MovimientoCajaResponseDTO(
                mov.getIdMovimientoCaja(),
                mov.getCaja().getIdCaja(),
                mov.getUsuario().getIdUsuario(),
                mov.getUsuario().getPersona().getNombre(),
                mov.getTipo().name(),
                mov.getMonto(),
                mov.getDescripcion(),
                mov.getReferenciaTipo() != null ? mov.getReferenciaTipo().name() : null,
                mov.getReferenciaId(),
                mov.getFecha()
        );
    }
}
