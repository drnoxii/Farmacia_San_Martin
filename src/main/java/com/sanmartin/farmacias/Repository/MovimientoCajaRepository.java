package com.sanmartin.farmacias.Repository;

import com.sanmartin.farmacias.Entity.MovimientoCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MovimientoCajaRepository extends JpaRepository<MovimientoCaja,Long> {

    List<MovimientoCaja> findByCajaIdCaja(Long idCaja);

    @Query("""
        SELECT COALESCE(SUM(m.monto), 0)
        FROM MovimientoCaja m
        WHERE m.caja.idCaja = :idCaja
          AND m.tipo IN :tipos
        """)
    BigDecimal sumMontoByCajaAndTipos(
            @Param("idCaja") Long idCaja,
            @Param("tipos") List<TipoMovimientoCaja> tipos
    );
}
