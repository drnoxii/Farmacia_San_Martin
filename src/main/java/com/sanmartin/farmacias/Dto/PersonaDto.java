package com.sanmartin.farmacias.Dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PersonaDto(
        @Parameter(hidden = true)
        long id,

        @NotBlank(message = "El nombre no puede quedar vacío")
        String nombre,

        @NotBlank(message = "Este campo no puede quedar vacio")
        String tipoDocumento,

        @NotBlank(message = "Este campo no puede quedar vacio")
        String numeroDocumento,

        @Pattern(
          regexp = "^9\\d{8}$",
          message = "El telefono debe tener 9 digítos y empezar con 9")
        String telefono,

        @NotBlank(message = "Este campo no puede quedar vacio")
        String direccion
) {
}
