package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(nullable = false, length = 150)
    private String nombreProv;

    @Column(nullable = false, unique = true, length = 11)
    private String RUCProv;

    @Column(nullable = false)
    private String telfonoProv;

    @Column(nullable = false)
    private String direccionProv;

    public Proveedor() {
    }

    public Proveedor(Long idProveedor, String nombreProv, String RUCProv, String telfonoProv, String direccionProv) {
        this.idProveedor = idProveedor;
        this.nombreProv = nombreProv;
        this.RUCProv = RUCProv;
        this.telfonoProv = telfonoProv;
        this.direccionProv = direccionProv;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getRUCProv() {
        return RUCProv;
    }

    public void setRUCProv(String RUCProv) {
        this.RUCProv = RUCProv;
    }

    public String getTelfonoProv() {
        return telfonoProv;
    }

    public void setTelfonoProv(String telfonoProv) {
        this.telfonoProv = telfonoProv;
    }

    public String getDireccionProv() {
        return direccionProv;
    }

    public void setDireccionProv(String direccionProv) {
        this.direccionProv = direccionProv;
    }
}
