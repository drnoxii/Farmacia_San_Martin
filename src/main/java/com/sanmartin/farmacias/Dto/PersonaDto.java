package com.sanmartin.farmacias.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PersonaDto(
        @NotBlank(message = "El nombre no puede quedar vacío")
        String nombreP,

        @NotBlank(message = "Este campo no puede quedar vacio")
        String tipoDocumentoP,

        @NotBlank(message = "Este campo no puede quedar vacio")
        String numeroDocumentoP,

        @Pattern(
          regexp = "^9\\d{8}$",
          message = "El telefono debe tener 9 digítos y empezar con 9")
        String telefonoPersona,

        @NotBlank(message = "Este campo no puede quedar vacio")
        String direccionPersona
) {
}
