package com.sanmartin.farmacias.Dto;

import com.sanmartin.farmacias.Entity.TipoMovimientoCaja;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Datos para registrar un movimiento de caja")
public record MovimientoCajaRequestDTO(

        @Schema(description = "ID de la caja a la que pertenece el movimiento", example = "1")
        @NotNull(message = "La caja es obligatoria")
        Long idCaja,

        @Schema(description = "ID del usuario que registra el movimiento", example = "1")
        @NotNull(message = "El usuario es obligatorio")
        Long idUsuario,

        @Schema(description = "Tipo de movimiento", example = "INGRESO")
        @NotNull(message = "El tipo es obligatorio")
        TipoMovimientoCaja tipo,

        @Schema(description = "Monto del movimiento", example = "50.00")
        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
        BigDecimal monto,

        @Schema(description = "Descripción o motivo del movimiento", example = "Ingreso extra de cambio")
        @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
        String descripcion
) {
}
