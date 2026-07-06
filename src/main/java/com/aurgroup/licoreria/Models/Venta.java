package com.aurgroup.licoreria.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sucursal", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private MetodoPago metodoPago;

    private String tipoComprobante;

    private String serie;

    private String numeroSerie;

    private LocalDateTime fecha;

    private BigDecimal total = BigDecimal.ZERO;

    private String estado = "Pendiente";

    private String origen = "Presencial";

    @OneToMany(
        mappedBy = "venta",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonIgnoreProperties({ "venta", "hibernateLazyInitializer", "handler" })
    private List<VentaDetalle> detalles = new ArrayList<>();

    public Venta() {}

    public Venta(
        Integer idVenta,
        Sucursal sucursal,
        Cliente cliente,
        Usuario usuario,
        MetodoPago metodoPago,
        String tipoComprobante,
        String serie,
        String numeroSerie,
        LocalDateTime fecha,
        BigDecimal total,
        String estado,
        String origen,
        List<VentaDetalle> detalles
    ) {
        this.idVenta = idVenta;
        this.sucursal = sucursal;
        this.cliente = cliente;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numeroSerie = numeroSerie;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.origen = origen;
        this.detalles = detalles;
    }

    @PrePersist
    public void prePersist() {
        if (this.fecha == null) {
            this.fecha = LocalDateTime.now();
        }

        if (this.total == null) {
            this.total = BigDecimal.ZERO;
        }

        if (this.estado == null || this.estado.isBlank()) {
            this.estado = "Pendiente";
        }

        if (this.origen == null || this.origen.isBlank()) {
            this.origen = "Presencial";
        }
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public List<VentaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
    }
}
