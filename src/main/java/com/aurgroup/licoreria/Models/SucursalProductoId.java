package com.aurgroup.licoreria.Models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SucursalProductoId implements Serializable {

    private Integer idSucursal;

    private Integer idProducto;

    public SucursalProductoId() {}

    public SucursalProductoId(Integer idSucursal, Integer idProducto) {
        this.idSucursal = idSucursal;
        this.idProducto = idProducto;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
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
