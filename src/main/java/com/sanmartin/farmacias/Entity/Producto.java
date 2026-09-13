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
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idLaboratorio")
    private Laboratorio laboratorio;

    @Column(nullable = false)
    private BigDecimal precioVentaProd;

    private Integer stockActualProd;
    private Integer stockMinimoProd;

    @Column(length = 1)
    private String recetaRequeProd;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

    public Producto() {
    }

    public Producto(Long idProducto, String codigoBarrasProd, String nombreProducto, Categoria categoria, Laboratorio laboratorio, BigDecimal precioVentaProd, Integer stockActualProd, Integer stockMinimoProd, String recetaRequeProd, Proveedor proveedor) {
        this.idProducto = idProducto;
        this.codigoBarrasProd = codigoBarrasProd;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.laboratorio = laboratorio;
        this.precioVentaProd = precioVentaProd;
        this.stockActualProd = stockActualProd;
        this.stockMinimoProd = stockMinimoProd;
        this.recetaRequeProd = recetaRequeProd;
        this.proveedor = proveedor;
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

    public BigDecimal getPrecioVentaProd() {
        return precioVentaProd;
    }

    public void setPrecioVentaProd(BigDecimal precioVentaProd) {
        this.precioVentaProd = precioVentaProd;
    }

    public Integer getStockActualProd() {
        return stockActualProd;
    }

    public void setStockActualProd(Integer stockActualProd) {
        this.stockActualProd = stockActualProd;
    }

    public Integer getStockMinimoProd() {
        return stockMinimoProd;
    }

    public void setStockMinimoProd(Integer stockMinimoProd) {
        this.stockMinimoProd = stockMinimoProd;
    }

    public String getRecetaRequeProd() {
        return recetaRequeProd;
    }

    public void setRecetaRequeProd(String recetaRequeProd) {
        this.recetaRequeProd = recetaRequeProd;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
