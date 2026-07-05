package com.aurgroup.licoreria.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Cliente
 */
@Entity
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idCliente;

  private String tipoCliente;

  private String tipoDocumento;

  private String numeroDocumento;

  private String nombres;

  private String apellidos;

  private String razonSocial;

  private String telefono;

  private String direccion;

  private String correo;

  private String estado;

  private LocalDateTime fechaRegistro;

  protected Cliente() {
  }

  public Cliente(String tipoCliente, String tipoDocumento, String nombres, String apellidos, String razonSocial, String telefono, String direccion, String correo, String estado) {
    this.tipoCliente = tipoCliente;
    this.tipoDocumento = tipoDocumento;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.razonSocial = razonSocial;
    this.telefono = telefono;
    this.direccion = direccion;
    this.correo = correo;
    this.estado = estado;
  }

  public Long getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Long idCliente) {
    this.idCliente = idCliente;
  }

  public String getTipoCliente() {
    return tipoCliente;
  }

  public void setTipoCliente(String tipoCliente) {
    this.tipoCliente = tipoCliente;
  }

  public String getTipoDocumento() {
    return tipoDocumento;
  }

  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
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

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
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

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public LocalDateTime getFechaRegistro() {
    return fechaRegistro;
  }

  public String getNumeroDocumento() {
  	return numeroDocumento;
  }

  public void setNumeroDocumento(String numeroDocumento) {
  	this.numeroDocumento = numeroDocumento;
  }

}
