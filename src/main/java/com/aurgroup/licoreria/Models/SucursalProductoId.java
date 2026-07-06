package com.aurgroup.licoreria.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SucursalProductoId implements Serializable {

    @Column(name = "id_sucursal")
    private Long idSucursal;

    @Column(name = "id_producto")
    private Integer idProducto;

    public SucursalProductoId() {}

    public SucursalProductoId(Long idSucursal, Integer idProducto) {
        this.idSucursal = idSucursal;
        this.idProducto = idProducto;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SucursalProductoId)) {
            return false;
        }

        SucursalProductoId that = (SucursalProductoId) o;

        return (
            Objects.equals(idSucursal, that.idSucursal) &&
            Objects.equals(idProducto, that.idProducto)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSucursal, idProducto);
    }
}
