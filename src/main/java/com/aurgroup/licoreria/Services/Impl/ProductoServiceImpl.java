package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.Producto;
import com.aurgroup.licoreria.Repositories.ProductoRepository;
import com.aurgroup.licoreria.Services.ProductoService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ProductoServiceImpl
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto findById(Integer idProducto) {
        return productoRepository
            .findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Integer idProducto) {
        productoRepository.deleteById(idProducto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        return productoRepository.save(producto);
    }
}
