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


    public Producto() {
    }

    public Producto(Long idProducto, String codigoBarrasProd, String nombreProducto, Categoria categoria, Laboratorio laboratorio, BigDecimal precioVenta, Integer stockActual, Integer stockMinimo, EstadoGeneral estado) {
        this.idProducto = idProducto;
        this.codigoBarrasProd = codigoBarrasProd;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.laboratorio = laboratorio;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoBarrasProd() {
        return codigoBarrasProd;
    }

    public void setCodigoBarrasProd(String codigoBarrasProd) {
        this.codigoBarrasProd = codigoBarrasProd;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }
}
