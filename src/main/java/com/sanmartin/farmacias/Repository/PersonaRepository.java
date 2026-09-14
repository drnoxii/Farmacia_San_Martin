package com.sanmartin.farmacias.Repository;

import com.sanmartin.farmacias.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByDni(String dni);

    boolean existsByDni(String dni);
}
