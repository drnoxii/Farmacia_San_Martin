package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Devolucion_Venta")
public class DevolucionVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDevolucion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDetalleVenta", nullable = false)
    private DetalleVenta detalleVenta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCaja", nullable = false)
    private Caja caja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMovimientoCaja")
    private MovimientoCaja movimientoCaja;

    @Column(name = "cantidad_devuelta", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadDevuelta;

    @Column(name = "monto_devuelto", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoDevuelto = BigDecimal.ZERO;

    @Column(length = 255)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_producto", nullable = false, length = 15)
    private EstadoProductoDevuelto estadoProducto;

    @Column(name = "fecha_devolucion", nullable = false)
    private LocalDateTime fechaDevolucion = LocalDateTime.now();

    public DevolucionVenta() {
    }

    public DevolucionVenta(Long idDevolucion, DetalleVenta detalleVenta, Usuario usuario, Caja caja, MovimientoCaja movimientoCaja, BigDecimal cantidadDevuelta, BigDecimal montoDevuelto, String motivo, EstadoProductoDevuelto estadoProducto, LocalDateTime fechaDevolucion) {
        this.idDevolucion = idDevolucion;
        this.detalleVenta = detalleVenta;
        this.usuario = usuario;
        this.caja = caja;
        this.movimientoCaja = movimientoCaja;
        this.cantidadDevuelta = cantidadDevuelta;
        this.montoDevuelto = montoDevuelto;
        this.motivo = motivo;
        this.estadoProducto = estadoProducto;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(Long idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public MovimientoCaja getMovimientoCaja() {
        return movimientoCaja;
    }

    public void setMovimientoCaja(MovimientoCaja movimientoCaja) {
        this.movimientoCaja = movimientoCaja;
    }

    public BigDecimal getCantidadDevuelta() {
        return cantidadDevuelta;
    }

    public void setCantidadDevuelta(BigDecimal cantidadDevuelta) {
        this.cantidadDevuelta = cantidadDevuelta;
    }

    public BigDecimal getMontoDevuelto() {
        return montoDevuelto;
    }

    public void setMontoDevuelto(BigDecimal montoDevuelto) {
        this.montoDevuelto = montoDevuelto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoProductoDevuelto getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(EstadoProductoDevuelto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
