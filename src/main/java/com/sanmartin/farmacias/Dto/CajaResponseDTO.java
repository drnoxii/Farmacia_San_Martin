package com.sanmartin.farmacias.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Información de una caja")
public record CajaResponseDTO(

        @Schema(description = "ID de la caja", example = "1")
        Long idCaja,

        @Schema(description = "ID del usuario que abrió la caja", example = "1")
        Long idUsuario,

        @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
        String nombreUsuario,

        @Schema(description = "Monto inicial con el que se abrió", example = "100.00")
        BigDecimal montoInicial,

        @Schema(description = "Monto contado al cierre", example = "450.50")
        BigDecimal montoFinal,

        @Schema(description = "Monto que debería haber si todo cuadra", example = "450.00")
        BigDecimal montoEsperado,

        @Schema(description = "Diferencia entre contado y esperado", example = "0.50")
        BigDecimal diferencia,

        @Schema(description = "Estado de la caja", example = "CERRADA")
        String estado,

        @Schema(description = "Fecha y hora de apertura", example = "2026-09-14T08:00:00")
        LocalDateTime fechaApertura,

        @Schema(description = "Fecha y hora de cierre", example = "2026-09-14T20:00:00")
        LocalDateTime fechaCierre,

        @Schema(description = "Total de ingresos durante el turno", example = "500.00")
        BigDecimal totalIngresos,

        @Schema(description = "Total de egresos durante el turno", example = "150.00")
        BigDecimal totalEgresos
) {
}
