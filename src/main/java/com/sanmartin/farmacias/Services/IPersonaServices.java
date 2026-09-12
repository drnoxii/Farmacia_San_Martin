package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.PersonaDto;

import java.util.List;
import java.util.Optional;

public interface IPersonaServices {
    List<PersonaDto> listarTodo();
    Optional<PersonaDto> buscarPorId(Long id);
    PersonaDto registrar(PersonaDto p);
    Optional<PersonaDto> actualizar(Long id, PersonaDto p);
    boolean eliminar(Long id);
}
