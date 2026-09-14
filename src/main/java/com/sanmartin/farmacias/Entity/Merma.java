package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Merma")
public class Merma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMerma;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idLote", nullable = false)
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MotivoMerma motivo;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "fecha_merma", nullable = false)
    private LocalDateTime fechaMerma = LocalDateTime.now();

    public Merma() {
    }

    public Merma(Long idMerma, Lote lote, Usuario usuario, BigDecimal cantidad, MotivoMerma motivo, String descripcion, LocalDateTime fechaMerma) {
        this.idMerma = idMerma;
        this.lote = lote;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.descripcion = descripcion;
        this.fechaMerma = fechaMerma;
    }

    public Long getIdMerma() {
        return idMerma;
    }

    public void setIdMerma(Long idMerma) {
        this.idMerma = idMerma;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public MotivoMerma getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoMerma motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaMerma() {
        return fechaMerma;
    }

    public void setFechaMerma(LocalDateTime fechaMerma) {
        this.fechaMerma = fechaMerma;
    }
}
