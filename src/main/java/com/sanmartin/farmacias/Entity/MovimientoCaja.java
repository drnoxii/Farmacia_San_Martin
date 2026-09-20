package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Movimiento_Caja")
public class MovimientoCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimientoCaja;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCaja", nullable = false)
    private Caja caja;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private TipoMovimientoCaja tipo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(length = 255)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "referencia_tipo", length = 25)
    private ReferenciaTipo referenciaTipo;

    @Column(name = "referencia_id")
    private Long referenciaId;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    public MovimientoCaja() {
    }

    public MovimientoCaja(Long idMovimientoCaja, Caja caja, Usuario usuario, TipoMovimientoCaja tipo, BigDecimal monto, String descripcion, ReferenciaTipo referenciaTipo, Long referenciaId, LocalDateTime fecha) {
        this.idMovimientoCaja = idMovimientoCaja;
        this.caja = caja;
        this.usuario = usuario;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.referenciaTipo = referenciaTipo;
        this.referenciaId = referenciaId;
        this.fecha = fecha;
    }

    public Long getIdMovimientoCaja() {
        return idMovimientoCaja;
    }

    public void setIdMovimientoCaja(Long idMovimientoCaja) {
        this.idMovimientoCaja = idMovimientoCaja;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoMovimientoCaja getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimientoCaja tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ReferenciaTipo getReferenciaTipo() {
        return referenciaTipo;
    }

    public void setReferenciaTipo(ReferenciaTipo referenciaTipo) {
        this.referenciaTipo = referenciaTipo;
    }

    public Long getReferenciaId() {
        return referenciaId;
    }

    public void setReferenciaId(Long referenciaId) {
        this.referenciaId = referenciaId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
