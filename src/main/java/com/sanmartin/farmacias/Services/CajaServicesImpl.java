package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.*;
import com.sanmartin.farmacias.Repository.CajaRepository;
import com.sanmartin.farmacias.Repository.MovimientoCajaRepository;
import com.sanmartin.farmacias.Repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

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
    public CajaResponseDTO abrirCaja(CajaRequestDTO dto) {
        return null;
    }

    @Override
    public CajaResponseDTO cerrarCaja(Long idCaja, CajaCierreRequestDTO dto) {
        return null;
    }

    @Override
    public CajaResponseDTO obtenerPorId(Long idCaja) {
        return null;
    }

    @Override
    public List<CajaResponseDTO> listar() {
        return List.of();
    }

    @Override
    public List<CajaResponseDTO> listarAbiertas() {
        return List.of();
    }

    @Override
    public List<CajaResponseDTO> listarPorUsuario(Long idUsuario) {
        return List.of();
    }

    @Override
    public List<CajaResponseDTO> listarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        return List.of();
    }

    @Override
    public MovimientoCajaResponseDTO registrarMovimiento(MovimientoCajaRequestDTO dto) {
        return null;
    }

    @Override
    public List<MovimientoCajaResponseDTO> listarMovimientos(Long idCaja) {
        return List.of();
    }
}
