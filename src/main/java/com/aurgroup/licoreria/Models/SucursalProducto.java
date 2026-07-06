package com.aurgroup.licoreria.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "sucursal_producto")
public class SucursalProducto {

    @EmbeddedId
    private SucursalProductoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("idSucursal")
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private Integer stock = 0;

    private Integer stockMinimo = 0;

    public SucursalProducto() {}

    public SucursalProducto(
        SucursalProductoId id,
        Sucursal sucursal,
        Producto producto,
        Integer stock,
        Integer stockMinimo
    ) {
        this.id = id;
        this.sucursal = sucursal;
        this.producto = producto;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public SucursalProductoId getId() {
        return id;
    }

    public void setId(SucursalProductoId id) {
        this.id = id;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
