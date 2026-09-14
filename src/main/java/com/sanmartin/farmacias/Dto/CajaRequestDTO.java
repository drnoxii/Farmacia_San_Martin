package com.sanmartin.farmacias.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Datos para abrir una caja")
public record CajaRequestDTO(

        @Schema(description = "ID del usuario que abre la caja", example = "1")
        @NotNull(message = "El usuario es obligatorio")
        Long idUsuario,

        @Schema(description = "Monto inicial con el que se abre la caja", example = "100.00")
        @NotNull(message = "El monto inicial es obligatorio")
        @DecimalMin(value = "0.0", message = "El monto inicial no puede ser negativo")
        BigDecimal montoInicial
) {
}
