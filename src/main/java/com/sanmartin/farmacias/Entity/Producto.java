package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false, length = 50)
    private String codigoBarrasProd;

    @Column(nullable = false, length = 100)
    private String nombreProducto;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idLaboratorio")
    private Laboratorio laboratorio;

    @Column(nullable = false)
    private BigDecimal precioVenta;

    private Integer stockActual;
    private Integer stockMinimo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EstadoGeneral estado = EstadoGeneral.ACTIVO;





}
