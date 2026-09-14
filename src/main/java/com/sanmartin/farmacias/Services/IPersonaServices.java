package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.PersonaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonaServices {
    PersonaDto crear(PersonaDto dto);

    PersonaDto obtenerPorId(Long id);

    List<PersonaDto> listar();

    PersonaDto actualizar(Long id, PersonaDto dto);

    void eliminar(Long id);
}

