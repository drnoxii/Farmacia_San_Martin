package com.sanmartin.farmacias.Repository;

import com.sanmartin.farmacias.Entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByCaja_idCaja(Long idCaja);
    List<Venta> findByUsuario_idUsuario(Long idUsuario);
}
