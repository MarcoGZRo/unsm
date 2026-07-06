package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Producto;
import java.util.List;

/**
 * ProductoService
 * @author M4riotaku
 */
public interface ProductoService {
    Producto findById(Integer idProducto);
    List<Producto> findAll();
    Producto createProducto(Producto producto);
    void deleteById(Integer idProducto);
    Producto updateProducto(Producto producto);
}
