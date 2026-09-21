package com.sanmartin.farmacias.Repository;


import com.sanmartin.farmacias.Entity.EstadoGeneral;
import com.sanmartin.farmacias.Entity.Rol;
import com.sanmartin.farmacias.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    boolean existsByCorreo(String usuario);

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findByPersonaNumeroDocumento(String numeroDocumento);

    List<Usuario> findByRol(Rol rol);

    List<Usuario> findByEstado(EstadoGeneral estado);

    List<Usuario> findByRolAndEstado(Rol rol, EstadoGeneral estado);


}
