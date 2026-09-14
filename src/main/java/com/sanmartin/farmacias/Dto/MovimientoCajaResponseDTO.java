package com.sanmartin.farmacias.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Información de un movimiento de caja")
public record MovimientoCajaResponseDTO(
        Long idMovimientoCaja,
        Long idCaja,
        Long idUsuario,
        String nombreUsuario,
        String tipo,
        BigDecimal monto,
        String descripcion,
        String referenciaTipo,
        Long referenciaId,
        LocalDateTime fecha
) {
}
