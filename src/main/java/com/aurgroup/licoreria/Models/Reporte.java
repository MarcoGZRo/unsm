package com.aurgroup.licoreria.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    private String tipo;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private LocalDateTime fechaGenerado;

    private BigDecimal totalVentas;

    private Integer totalTransacciones = 0;

    @PrePersist
    public void onCreate() {
        this.fechaGenerado = LocalDateTime.now();
    }

    public Reporte() {
    }

    public Reporte(Long idReporte, String tipo, LocalDate fechaInicio, LocalDate fechaFin, LocalDateTime fechaGenerado, BigDecimal totalVentas, Integer totalTransacciones) {
        this.idReporte = idReporte;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaGenerado = fechaGenerado;
        this.totalVentas = totalVentas;
        this.totalTransacciones = totalTransacciones;
    }

    public Long getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Long idReporte) {
        this.idReporte = idReporte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDateTime getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(LocalDateTime fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }

    public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Integer getTotalTransacciones() {
        return totalTransacciones;
    }

    public void setTotalTransacciones(Integer totalTransacciones) {
        this.totalTransacciones = totalTransacciones;
    }
}
