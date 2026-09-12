package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.PersonaDto;
import com.sanmartin.farmacias.Entity.Persona;
import com.sanmartin.farmacias.Repository.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonaServices{
    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public List<PersonaDto> listarTodo() {
        return this.personaRepository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public Optional<PersonaDto> buscarPorId(Long id) {
        return this.personaRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public PersonaDto registrar(PersonaDto p) {

        return null;
    }

    @Override
    public Optional<PersonaDto> actualizar(Long id, PersonaDto p) {
        return Optional.empty();
    }

    @Override
    public boolean eliminar(Long id) {
        return false;
    }

    private PersonaDto convertToDto(Persona p){
        return new PersonaDto(p.getNombrePersona(),
                p.getTipoDocumentoP(),
                p.getNumeroDocumentoP(),
                p.getTelefonoPersona(),
                p.getDireccionPesona());
    }
}
