package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ICajaServices {

    CajaResponseDTO abrirCaja(CajaRequestDTO dto);

    CajaResponseDTO cerrarCaja(Long idCaja, CajaCierreRequestDTO dto);

    CajaResponseDTO obtenerPorId(Long idCaja);

    List<CajaResponseDTO> listar();

    List<CajaResponseDTO> listarAbiertas();

    List<CajaResponseDTO> listarPorUsuario(Long idUsuario);

    List<CajaResponseDTO> listarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta);

    MovimientoCajaResponseDTO registrarMovimiento(MovimientoCajaRequestDTO dto);

    List<MovimientoCajaResponseDTO> listarMovimientos(Long idCaja);
}
