package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, unique = true)
    private RolNombre nombreRol;

    @Column(length = 150)
    private String descriptionRol;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios= new ArrayList<>();

    public Rol() {
    }

    public Rol(Long idRol, RolNombre nombreRol, String descriptionRol, List<Usuario> usuarios) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descriptionRol = descriptionRol;
        this.usuarios = usuarios;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public RolNombre getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(RolNombre nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescriptionRol() {
        return descriptionRol;
    }

    public void setDescriptionRol(String descriptionRol) {
        this.descriptionRol = descriptionRol;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
