package com.sanmartin.farmacias.Repository;


import com.sanmartin.farmacias.Entity.Rol;
import com.sanmartin.farmacias.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    Optional<Usuario> findByPersonaDni(String dni);

    boolean existsByPersonaDni(String dni);

    List<Usuario> findByRol(Rol rol);


}
