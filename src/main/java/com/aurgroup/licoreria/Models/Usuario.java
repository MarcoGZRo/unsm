package com.aurgroup.licoreria.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import com.aurgroup.licoreria.Models.Enums.Estado;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nombre_usuario", nullable = false, length = 80)
    private String nombreUsuario;

    @Column(nullable = false, unique = true, length = 120)
    private String correo;

    @Column(nullable = false, length = 255)
    private String clave;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    protected Usuario() {
    }
	
	public Integer getId() {
		return id;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public Rol getRol() {
		return rol;
	}
	
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}

enum Rol {
    admin,
    vendedor
}

