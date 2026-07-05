package com.aurgroup.licoreria.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_sucursal", nullable = false)
	private Sucursal sucursal;

    @Column(name = "nombre_usuario", nullable = false, length = 80)
    private String nombreUsuario;

    @Column(nullable = false, unique = true, length = 120)
    private String correo;

    @Column(nullable = false, length = 255)
    private String clave;

	private String rol;

    private String estado;

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
	
	public String getRol() {
		return rol;
	}
	
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public void setRol(String rol) {
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
	public Sucursal getSucursal() {
    return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
}

