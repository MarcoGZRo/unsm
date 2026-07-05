package com.aurgroup.licoreria.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

/**
 * Caja
 */
@Entity
public class Caja {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idCaja;
  @ManyToMany
  @JoinColumn(name = "id_sucursal", nullable = false)
  private Sucursal sucursal;
  @Column(unique = true)
  private String codigo;

  private String nombre;

  private String estado;

  protected Caja() {
  }

  public Caja(String codigo, String nombre, String estado) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.estado = estado;
  }

  public Integer getIdCaja() {
    return idCaja;
  }

  public void setIdCaja(Integer idCaja) {
    this.idCaja = idCaja;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
  public Sucursal getSucursal() {
    return sucursal;
  }
  public void setSucursal(Sucursal sucursal) {
    this.sucursal = sucursal;
  }
}
