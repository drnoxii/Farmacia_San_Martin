package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(nullable = false, unique = true, length = 11)
    private String ruc;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private EstadoGeneral estadoGeneral;

    public Proveedor() {
    }

    public Proveedor(Long idProveedor, String ruc, String nombre, String telefono, String direccion, EstadoGeneral estadoGeneral) {
        this.idProveedor = idProveedor;
        this.ruc = ruc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estadoGeneral = estadoGeneral;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public EstadoGeneral getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(EstadoGeneral estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }
}
