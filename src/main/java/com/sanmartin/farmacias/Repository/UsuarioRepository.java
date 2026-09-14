package com.sanmartin.farmacias.Repository;

import com.sanmartin.farmacias.Entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    boolean existsByUsuario(String usuario);
    Optional<Usuario> findByUsuario(String usuario);
}
