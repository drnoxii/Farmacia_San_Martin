package com.sanmartin.farmacias.Dto;

import com.sanmartin.farmacias.Entity.Rol;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record UsuarioDTO(
        @Parameter(hidden = true)
        Long idUsuario,

        @NotNull(message = "Este campo no puede estar en blanco")
        @Valid
        PersonaDto persona,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        @Size(min = 6, max = 200)
        String correo,

        @NotBlank(message = "La contraseña es obligatorio")
        @Size(min = 6, message = "El contraseña debe tener almenos 6 caracteres")
        String contraseña,

        @NotNull(message = "El rol es obligatorio")
        Rol rol
) {
}
