package com.sanmartin.farmacias.Dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PersonaDto(
        @Schema(description = "ID de la persona (se ignora al crear)", example = "1")
        Long idPersona,

        @Schema(description = "DNI de la persona (8 dígitos)", example = "12345678")
        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
        String dni,

        @Schema(description = "Nombre completo", example = "Juan Pérez")
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
        String nombre,

        @Schema(description = "Teléfono de contacto", example = "999888777")
        @Size(max = 15, message = "El teléfono no puede superar los 15 caracteres")
        String telefono,

        @Schema(description = "Dirección", example = "Av. Lima 123")
        @Size(max = 250, message = "La dirección no puede superar los 250 caracteres")
        String direccion
) {
}
