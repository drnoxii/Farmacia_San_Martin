package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(nullable = false, length = 150)
    private String nombrePersona;

    @Column(nullable = false, length = 10)
    private String tipoDocumentoP;

    @Column(nullable = false, unique = true, length = 15)
    private String numeroDocumentoP;

    private String telefonoPersona;
    private String direccionPesona;

    public Persona() {
    }

    public Persona(Long idPersona, String nombrePersona, String tipoDocumentoP, String numeroDocumentoP, String telefonoPersona, String direccionPesona) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.tipoDocumentoP = tipoDocumentoP;
        this.numeroDocumentoP = numeroDocumentoP;
        this.telefonoPersona = telefonoPersona;
        this.direccionPesona = direccionPesona;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getTipoDocumentoP() {
        return tipoDocumentoP;
    }

    public void setTipoDocumentoP(String tipoDocumentoP) {
        this.tipoDocumentoP = tipoDocumentoP;
    }

    public String getNumeroDocumentoP() {
        return numeroDocumentoP;
    }

    public void setNumeroDocumentoP(String numeroDocumentoP) {
        this.numeroDocumentoP = numeroDocumentoP;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public String getDireccionPesona() {
        return direccionPesona;
    }

    public void setDireccionPesona(String direccionPesona) {
        this.direccionPesona = direccionPesona;
    }
}
