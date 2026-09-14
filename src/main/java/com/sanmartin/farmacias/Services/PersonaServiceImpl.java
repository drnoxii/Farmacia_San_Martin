package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.PersonaDto;
import com.sanmartin.farmacias.Entity.Persona;
import com.sanmartin.farmacias.Repository.PersonaRepository;
import com.sanmartin.farmacias.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonaServices {

    private final PersonaRepository personaRepository;
    private final UsuarioRepository usuarioRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository,
                              UsuarioRepository usuarioRepository) {
        this.personaRepository = personaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public PersonaDto crear(PersonaDto dto) {

        if (personaRepository.existsByDni(dto.dni())) {
            throw new RuntimeException("Ya existe una persona con ese DNI");
        }

        Persona persona = new Persona();
        persona.setDni(dto.dni());
        persona.setNombre(dto.nombre());
        persona.setTelefono(dto.telefono());
        persona.setDireccion(dto.direccion());

        persona = personaRepository.save(persona);
        return toDTO(persona);
    }

    @Override
    public PersonaDto obtenerPorId(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        return toDTO(persona);
    }


    @Override
    public List<PersonaDto> listar() {
        return personaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public PersonaDto actualizar(Long id, PersonaDto dto) {

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        if (!persona.getDni().equals(dto.dni())
                && personaRepository.existsByDni(dto.dni())) {
            throw new RuntimeException("Ya existe otra persona con ese DNI");
        }

        persona.setDni(dto.dni());
        persona.setNombre(dto.nombre());
        persona.setTelefono(dto.telefono());
        persona.setDireccion(dto.direccion());

        persona = personaRepository.save(persona);
        return toDTO(persona);
    }


    @Override
    @Transactional
    public void eliminar(Long id) {

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        boolean tieneUsuario = usuarioRepository.findAll().stream()
                .anyMatch(u -> u.getPersona() != null
                        && u.getPersona().getIdPersona().equals(id));

        if (tieneUsuario) {
            throw new RuntimeException("No se puede eliminar: la persona tiene un usuario asociado");
        }

        personaRepository.delete(persona);
    }


    private PersonaDto toDTO(Persona persona) {
        return new PersonaDto(
                persona.getIdPersona(),
                persona.getDni(),
                persona.getNombre(),
                persona.getTelefono(),
                persona.getDireccion()
        );
    }
}
