package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuaio;

    @Column(nullable = false)
    private String correoUsuario;

    @Column(nullable = false)
    private String contraUsuario;

    private String estadoUsu;

    @ManyToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Long idUsuaio, String correoUsuario, String contraUsuario, String estadoUsu, Persona persona, Rol rol) {
        this.idUsuaio = idUsuaio;
        this.correoUsuario = correoUsuario;
        this.contraUsuario = contraUsuario;
        this.estadoUsu = estadoUsu;
        this.persona = persona;
        this.rol = rol;
    }

    public Long getIdUsuaio() {
        return idUsuaio;
    }

    public void setIdUsuaio(Long idUsuaio) {
        this.idUsuaio = idUsuaio;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContraUsuario() {
        return contraUsuario;
    }

    public void setContraUsuario(String contraUsuario) {
        this.contraUsuario = contraUsuario;
    }

    public String getEstadoUsu() {
        return estadoUsu;
    }

    public void setEstadoUsu(String estadoUsu) {
        this.estadoUsu = estadoUsu;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
