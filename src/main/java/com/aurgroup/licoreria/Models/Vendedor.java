package com.aurgroup.licoreria.Models;

import org.springframework.data.annotation.Id;
import com.aurgroup.licoreria.Models.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import com.aurgroup.licoreria.Models.Enums.Estado;

/**
 * Vendedor
 * realizado por M4rio
 */
@Entity
public class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVendedor;
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
	private Usuario usuario;

	private String dni;
    
	private String nombres;
    
	private String apellidos;
    
	private String telefono;

    @Enumerated(EnumType.STRING)
	private Estado estado;

	protected Vendedor (){}
    
	public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdAdministrador(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

	public Usuario getUsuario() {
        return usuario;
    }

    public void setIdUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.apellidos = apellidos;
    }
}

