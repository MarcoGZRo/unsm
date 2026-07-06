package com.aurgroup.licoreria.Controllers;

import com.aurgroup.licoreria.Models.Producto;
import com.aurgroup.licoreria.Services.ProductoService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductoController
 * @author M4riotaku
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{idProducto}")
    public Producto getProductoById(@PathVariable Integer idProducto) {
        return productoService.findById(idProducto);
    }
}
