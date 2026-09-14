package com.sanmartin.farmacias.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", nullable = false, unique = true)
    private Persona persona;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "contrasena_hash", nullable = false, length = 255)
    private String contrasenaHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoGeneral estado = EstadoGeneral.ACTIVO;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Caja> cajas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<MovimientoCaja> movimientosCaja = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<DevolucionCompra> devolucionesCompra = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<DevolucionVenta> devolucionesVenta = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Merma> mermas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<MovimientoInventario> movimientosInventario = new ArrayList<>();

    public Usuario() {
    }
}
