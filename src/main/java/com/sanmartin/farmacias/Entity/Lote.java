package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Lote")
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDetalleCompra", nullable = false)
    private DetalleCompra detalleCompra;

    @Column(name = "numero_lote", nullable = false, length = 20)
    private String numeroLote;

    @Column(name = "stock_lote", nullable = false, precision = 10, scale = 2)
    private BigDecimal stockLote= BigDecimal.ZERO;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso=LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoLote estado = EstadoLote.ACTIVO;

    @OneToMany(mappedBy = "lote")
    private List<DevolucionCompra> devolucionesCompra = new ArrayList<>();

    @OneToMany(mappedBy = "lote")
    private List<DetalleVenta> detallesVenta = new ArrayList<>();

    @OneToMany(mappedBy = "lote")
    private List<Merma> mermas = new ArrayList<>();

    @OneToMany(mappedBy = "lote")
    private List<MovimientoInventario> movimientosInventario = new ArrayList<>();

    public Lote() {
    }

    public Lote(Long idLote, Producto producto, DetalleCompra detalleCompra, String numeroLote, BigDecimal stockLote, LocalDate fechaVencimiento, LocalDateTime fechaIngreso, EstadoLote estado, List<DevolucionCompra> devolucionesCompra, List<DetalleVenta> detallesVenta, List<Merma> mermas, List<MovimientoInventario> movimientosInventario) {
        this.idLote = idLote;
        this.producto = producto;
        this.detalleCompra = detalleCompra;
        this.numeroLote = numeroLote;
        this.stockLote = stockLote;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.devolucionesCompra = devolucionesCompra;
        this.detallesVenta = detallesVenta;
        this.mermas = mermas;
        this.movimientosInventario = movimientosInventario;
    }

    public Long getIdLote() {
        return idLote;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public DetalleCompra getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(DetalleCompra detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public BigDecimal getStockLote() {
        return stockLote;
    }

    public void setStockLote(BigDecimal stockLote) {
        this.stockLote = stockLote;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public EstadoLote getEstado() {
        return estado;
    }

    public void setEstado(EstadoLote estado) {
        this.estado = estado;
    }

    public List<DevolucionCompra> getDevolucionesCompra() {
        return devolucionesCompra;
    }

    public void setDevolucionesCompra(List<DevolucionCompra> devolucionesCompra) {
        this.devolucionesCompra = devolucionesCompra;
    }

    public List<DetalleVenta> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    public List<Merma> getMermas() {
        return mermas;
    }

    public void setMermas(List<Merma> mermas) {
        this.mermas = mermas;
    }

    public List<MovimientoInventario> getMovimientosInventario() {
        return movimientosInventario;
    }

    public void setMovimientosInventario(List<MovimientoInventario> movimientosInventario) {
        this.movimientosInventario = movimientosInventario;
    }
}
