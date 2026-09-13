package com.sanmartin.farmacias.Dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProveedorDto(
        @Parameter(hidden = true)
        long idProv,

        @NotBlank(message = "Este campo no puede estar vacío")
        String nombreProv,

        @Pattern(
          regexp = "^(10|15|17|20)\\d{9}$\n",
          message = "Ingrese correctamentre "
        )
        String rucProv,

        @Pattern(
                regexp = "^9\\d{8}$",
                message = "El telefono debe tener 9 digítos y empezar con 9")
        String telefonoProv,

        @NotBlank(message = "Este campo no puede estar vacío")
        String direccionProv
) {
}
