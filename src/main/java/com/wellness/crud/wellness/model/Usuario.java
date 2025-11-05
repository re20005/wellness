package com.wellness.crud.wellness.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombre;

    @Email
    @NotBlank
    @Column(name = "correo_usuario", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Size(min = 6)
    @Column(name = "contrasena_usuario", nullable = false)
    private String password;

    @Column(name = "rol_usuario", nullable = false)
    private String rol; // 'admin', 'profesional', 'cliente'

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    public Usuario() {}

    // Getters y setters
    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public Timestamp getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Timestamp fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
