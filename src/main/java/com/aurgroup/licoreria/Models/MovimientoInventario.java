package com.aurgroup.licoreria.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;

	@ManyToOne
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

	@ManyToOne
    @JoinColumn(name = "id_administrador", nullable = true)
    private Administrador administrador;
    
	private String tipo;

    private String motivo;

    private Integer cantidad;

    private LocalDateTime fecha;


    public MovimientoInventario() {
    }

    public MovimientoInventario(Integer idMovimiento, Producto producto, Usuario usuario, String tipo, String motivo, Integer cantidad, LocalDateTime fecha) {
        this.idMovimiento = idMovimiento;
        this.producto = producto;
        this.usuario = usuario;
        this.tipo = tipo;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCausa() {
        return motivo;
    }

    public void setCausa(String motivo) {
        this.motivo = motivo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
