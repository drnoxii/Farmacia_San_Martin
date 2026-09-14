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
    @Transactional(readOnly = true)
    public List<PersonaDto> listarTodo() {
        return this.personaRepository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public Optional<PersonaDto> buscarPorId(Long id) {
        return this.personaRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public PersonaDto registrar(PersonaDto p) {
        Persona person= new Persona();
        person.setNombre(p.nombre());
        person.setTipoDocumento(p.tipoDocumento());
        person.setNumeroDocumento(p.numeroDocumento());
        person.setTelefono(p.telefono());
        person.setDireccion(p.telefono());
        return this.convertToDto((Persona)this.personaRepository.save(person));
    }

    @Override
    public Optional<PersonaDto> actualizar(Long id, PersonaDto p) {
        return this.personaRepository.findById(id).map((pr) ->{
            pr.setNombre(p.nombre());
            pr.setTipoDocumento(p.tipoDocumento());
            pr.setNumeroDocumento(p.numeroDocumento());
            pr.setTelefono(p.telefono());
            pr.setDireccion(p.direccion());
            return this.convertToDto((Persona)this.personaRepository.save(pr));
        });
    }

    @Override
    public boolean eliminar(Long id) {
        if (this.personaRepository.existsById(id)) {
            this.personaRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    private PersonaDto convertToDto(Persona p){
        return new PersonaDto(
                p.getIdPersona(),
                p.getNombre(),
                p.getTipoDocumento(),
                p.getNumeroDocumento(),
                p.getTelefono(),
                p.getDireccion());
    }
}
