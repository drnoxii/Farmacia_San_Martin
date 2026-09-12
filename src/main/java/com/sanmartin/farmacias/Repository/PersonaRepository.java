package com.sanmartin.farmacias.Repository;

import com.sanmartin.farmacias.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
