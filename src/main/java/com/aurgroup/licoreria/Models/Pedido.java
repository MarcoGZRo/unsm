package com.aurgroup.licoreria.Models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    private LocalDateTime fecha;

    private BigDecimal total = BigDecimal.ZERO;

    private String estado;

    private String telefono;

	private String referencia;

    private String direccion;

    private String correo;

    @PrePersist
    public void onCreate() {
        this.fecha = LocalDateTime.now();
    }

    public Pedido() {
    }

    public Pedido(Long idPedido, Cliente cliente, MetodoPago metodoPago, String referencia, LocalDateTime fecha, BigDecimal total, String estado, String telefono, String direccion, String correo) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.telefono = telefono;
        this.direccion = direccion;
		this.referencia = referencia;
        this.correo = correo;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

	public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
