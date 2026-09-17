package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.UsuarioDTO;
import com.sanmartin.farmacias.Entity.Rol;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUsuarioServices {
    List<UsuarioDTO> listarUsuarios();
    Optional<UsuarioDTO> buscarUsuarioPorId(Long id);
    UsuarioDTO registrarUsuario(UsuarioDTO us);
    Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioDTO us);
    boolean eliminarUsuario(Long id);

    Optional<UsuarioDTO> findByCorreo(String correo);
    Optional<UsuarioDTO> findByPersonaDni(String dni);
    List<UsuarioDTO> findByRol(Rol rol);
}
