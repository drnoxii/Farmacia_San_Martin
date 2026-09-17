package com.sanmartin.farmacias.Services;


import com.sanmartin.farmacias.Dto.PersonaDto;
import com.sanmartin.farmacias.Dto.UsuarioDTO;
import com.sanmartin.farmacias.Entity.Persona;
import com.sanmartin.farmacias.Entity.Rol;
import com.sanmartin.farmacias.Entity.Usuario;
import com.sanmartin.farmacias.Exception.DuplicateResourceException;
import com.sanmartin.farmacias.Repository.PersonaRepository;
import com.sanmartin.farmacias.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioServices{
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        return this.usuarioRepository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> buscarUsuarioPorId(Long id) {
        return this.usuarioRepository.findById(id).map(this::convertToDto);
    }

    @Override
    @Transactional
    public UsuarioDTO registrarUsuario(UsuarioDTO us) {
        //Crear una Excepción personalizada(hecho)
        if (personaRepository.existsByDni(us.persona().dni())){
            throw new DuplicateResourceException("Ya existe una persona con ese DNI");
        }
        if (usuarioRepository.existsByCorreo(us.correo())){
            throw new DuplicateResourceException("Ya existe un usuario con el mismo correo");
        }
        //PersonaDto a Persona (Esto puede dar error al levantar el proyecto o al querer crear un usuario/persona)
        Persona persona= new Persona();
        persona.setNombre(us.persona().nombre());
        persona.setDni(us.persona().dni());
        persona.setTelefono(us.persona().telefono());
        persona.setDireccion(us.persona().direccion());

        persona = personaRepository.save(persona);

        //Crear un usuario y ponerle la persona
        Usuario usuario = new Usuario();
        usuario.setPersona(persona);
        usuario.setCorreo(us.correo());
        usuario.setPassword(us.contraseña());
        usuario.setRol(us.rol());

        usuario= usuarioRepository.save(usuario);
        return convertToDto(usuario);
    }

    @Override
    public Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioDTO us) {
        return this.usuarioRepository.findById(id).map(user -> {
            //Actualizar Persona
            Persona persona = user.getPersona();
            persona.setNombre(us.persona().nombre());
            persona.setDni(us.persona().dni());
            persona.setDireccion(us.persona().direccion());
            persona.setTelefono(us.persona().telefono());
            personaRepository.save(persona);

            //Actualizar el Usuario
            user.setCorreo(us.correo());
            user.setPassword(us.contraseña());
            user.setRol(us.rol());
            usuarioRepository.save(user);
            return convertToDto(user);
        });
    }

    @Override
    public boolean eliminarUsuario(Long id) {
        Optional<Usuario> userOpt= usuarioRepository.findById(id);
        if (userOpt.isEmpty()){
            return false;
        }
        Usuario usuario = userOpt.get();
        Persona persona = userOpt.get().getPersona();

        //Primero se elimina al que tiene la FK
        usuarioRepository.delete(usuario);
        usuarioRepository.flush();//fuerza a que Usuario se elimine primero antes que Persona

        //Después se elimina la Persona relacionada
        personaRepository.delete(persona);
        return true;

        /*
        if (this.usuarioRepository.existsById(id)){
            Optional<Usuario> userOpt= usuarioRepository.findById(id);
            Usuario usuario = userOpt.get();
            Persona persona = userOpt.get().getPersona();

            //Eliminar primero al que tiene la FK
            usuarioRepository.delete(usuario);//OJO la Caja está relacionada con el Usuario, la eliminación física dará problemas
            usuarioRepository.flush();//fuerza a que Usuario se elimine primero antes que Persona

            //Eliminar luego la Persona relacionada
            personaRepository.delete(persona);
            return true;
        }else {
            return false;
        }
       */
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByCorreo(String correo) {
        return this.usuarioRepository.findByCorreo(correo).map(this::convertToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByPersonaDni(String dni) {
        return this.usuarioRepository.findByPersonaDni(dni).map(this::convertToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> findByRol(Rol rol) {
        return this.usuarioRepository.findByRol(rol).stream().map(this::convertToDto).toList();
    }

    private UsuarioDTO convertToDto (Usuario usuario){
        Persona persona= usuario.getPersona();
        PersonaDto personaDto = new PersonaDto(
                persona.getIdPersona(),
                persona.getDni(),
                persona.getNombre(),
                persona.getTelefono(),
                persona.getDireccion()
        );
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                personaDto,
                usuario.getCorreo(),
                usuario.getPassword(),
                usuario.getRol()
        );
    }

}
