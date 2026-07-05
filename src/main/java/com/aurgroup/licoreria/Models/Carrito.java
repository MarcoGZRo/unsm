package com.aurgroup.licoreria.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carrito {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idCarrito;

  @ManyToOne
  @JoinColumn(name = "id_cliente", nullable = false)
  private Cliente cliente;

  private LocalDateTime fechaCreacion;

  private String estado;

  protected Carrito() {
  }

  public Carrito(Integer idCarrito, Cliente cliente, LocalDateTime fechaCreacion, String estado) {
    this.idCarrito = idCarrito;
    this.cliente = cliente;
    this.fechaCreacion = fechaCreacion;
    this.estado = estado;
  }

  public Integer getIdCarrito() {
    return idCarrito;
  }

  public void setIdCarrito(Integer idCarrito) {
    this.idCarrito = idCarrito;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
}
