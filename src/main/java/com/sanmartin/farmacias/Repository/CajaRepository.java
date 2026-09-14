package com.sanmartin.farmacias.Repository;

import com.sanmartin.farmacias.Entity.Caja;
import com.sanmartin.farmacias.Entity.EstadoCaja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CajaRepository extends JpaRepository<Caja,Long> {

    Optional<Caja> findByUsuarioIdUsuarioAndEstado(Long idUsuario, EstadoCaja estado);
    List<Caja> findByEstado(EstadoCaja estado);
    List<Caja> findByFechaAperturaBetween(LocalDateTime desde, LocalDateTime hasta);
    List<Caja> findByUsuarioIdUsuario(Long idUsuario);

}
