package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.*;
import com.aurgroup.licoreria.Repositories.*;
import com.aurgroup.licoreria.Services.CompraService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final SucursalRepository sucursalRepository;
    private final ProveedorRepository proveedorRepository;
    private final AdministradorRepository administradorRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;
    private final SucursalProductoRepository sucursalProductoRepository;
    private final MovimientoInventarioRepository movimientoInventarioRepository;

    public CompraServiceImpl(
        CompraRepository compraRepository,
        SucursalRepository sucursalRepository,
        ProveedorRepository proveedorRepository,
        AdministradorRepository administradorRepository,
        MetodoPagoRepository metodoPagoRepository,
        ProductoRepository productoRepository,
        MarcaRepository marcaRepository,
        CategoriaRepository categoriaRepository,
        SucursalProductoRepository sucursalProductoRepository,
        MovimientoInventarioRepository movimientoInventarioRepository
    ) {
        this.compraRepository = compraRepository;
        this.sucursalRepository = sucursalRepository;
        this.proveedorRepository = proveedorRepository;
        this.administradorRepository = administradorRepository;
        this.metodoPagoRepository = metodoPagoRepository;
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
        this.sucursalProductoRepository = sucursalProductoRepository;
        this.movimientoInventarioRepository = movimientoInventarioRepository;
    }

    @Override
    @Transactional
    public Compra createCompra(Compra compra) {
        validarCompra(compra);

        Sucursal sucursal = sucursalRepository
            .findById(compra.getSucursal().getIdSucursal())
            .orElseThrow(() ->
                new RuntimeException(
                    "Sucursal no encontrada con ID: " +
                        compra.getSucursal().getIdSucursal()
                )
            );

        Proveedor proveedor = proveedorRepository
            .findById(compra.getProveedor().getIdProveedor())
            .orElseThrow(() ->
                new RuntimeException(
                    "Proveedor no encontrado con ID: " +
                        compra.getProveedor().getIdProveedor()
                )
            );

        Administrador administrador = administradorRepository
            .findById(compra.getAdministrador().getIdAdministrador())
            .orElseThrow(() ->
                new RuntimeException(
                    "Administrador no encontrado con ID: " +
                        compra.getAdministrador().getIdAdministrador()
                )
            );

        MetodoPago metodoPago = metodoPagoRepository
            .findById(compra.getMetodoPago().getIdMetodoPago())
            .orElseThrow(() ->
                new RuntimeException(
                    "Método de pago no encontrado con ID: " +
                        compra.getMetodoPago().getIdMetodoPago()
                )
            );

        if (administrador.getUsuario() == null) {
            throw new RuntimeException(
                "El administrador no tiene un usuario asociado."
            );
        }

        compra.setSucursal(sucursal);
        compra.setProveedor(proveedor);
        compra.setAdministrador(administrador);
        compra.setUsuario(administrador.getUsuario());
        compra.setMetodoPago(metodoPago);

        if (compra.getFechaRegistro() == null) {
            compra.setFechaRegistro(LocalDate.now());
        }

        if (compra.getEstado() == null || compra.getEstado().isBlank()) {
            compra.setEstado("Recibida");
        }

        BigDecimal totalCompra = BigDecimal.ZERO;

        for (CompraDetalle detalle : compra.getDetalles()) {
            validarDetalle(detalle);

            Producto producto = obtenerOCrearProducto(detalle);

            detalle.setProducto(producto);

            if (detalle.getPrecioUnitario() == null) {
                if (producto.getPrecioCompra() != null) {
                    detalle.setPrecioUnitario(producto.getPrecioCompra());
                } else {
                    throw new RuntimeException(
                        "El producto " +
                            producto.getNombre() +
                            " no tiene precio de compra."
                    );
                }
            }

            BigDecimal subtotal = detalle
                .getPrecioUnitario()
                .multiply(BigDecimal.valueOf(detalle.getCantidad()));

            detalle.setSubtotal(subtotal);
            detalle.setCompra(compra);

            totalCompra = totalCompra.add(subtotal);
        }

        compra.setTotal(totalCompra);

        Compra compraGuardada = compraRepository.save(compra);

        for (CompraDetalle detalle : compraGuardada.getDetalles()) {
            Producto producto = detalle.getProducto();

            SucursalProducto stockSucursal = sucursalProductoRepository
                .findBySucursal_IdSucursalAndProducto_IdProducto(
                    sucursal.getIdSucursal(),
                    producto.getIdProducto()
                )
                .orElseGet(() ->
                    crearStockSucursalProducto(sucursal, producto)
                );

            stockSucursal.setStock(
                stockSucursal.getStock() + detalle.getCantidad()
            );

            sucursalProductoRepository.save(stockSucursal);

            MovimientoInventario movimiento = new MovimientoInventario();

            movimiento.setSucursal(sucursal);
            movimiento.setProducto(producto);
            movimiento.setUsuario(administrador.getUsuario());
            movimiento.setFecha(LocalDateTime.now());
            movimiento.setTipo("Entrada");
            movimiento.setCantidad(detalle.getCantidad());
            movimiento.setMotivo(
                "Compra " +
                    compraGuardada.getSerie() +
                    "-" +
                    compraGuardada.getNumeroSerie()
            );

            movimientoInventarioRepository.save(movimiento);
        }

        return compraGuardada;
    }

    private Producto obtenerOCrearProducto(CompraDetalle detalle) {
        Producto productoRecibido = detalle.getProducto();

        if (productoRecibido.getIdProducto() != null) {
            Producto productoExistente = productoRepository
                .findById(productoRecibido.getIdProducto())
                .orElseThrow(() ->
                    new RuntimeException(
                        "Producto no encontrado con ID: " +
                            productoRecibido.getIdProducto()
                    )
                );

            if (detalle.getPrecioUnitario() != null) {
                productoExistente.setPrecioCompra(detalle.getPrecioUnitario());
                productoRepository.save(productoExistente);
            }

            return productoExistente;
        }

        validarProductoNuevo(productoRecibido);

        Marca marca = marcaRepository
            .findById(productoRecibido.getMarca().getIdMarca())
            .orElseThrow(() ->
                new RuntimeException(
                    "Marca no encontrada con ID: " +
                        productoRecibido.getMarca().getIdMarca()
                )
            );

        Categoria categoria = categoriaRepository
            .findById(productoRecibido.getCategoria().getIdCategoria())
            .orElseThrow(() ->
                new RuntimeException(
                    "Categoría no encontrada con ID: " +
                        productoRecibido.getCategoria().getIdCategoria()
                )
            );

        Producto productoExistentePorDatos = productoRepository
            .findFirstByNombreIgnoreCaseAndMarca_IdMarcaAndCategoria_IdCategoria(
                productoRecibido.getNombre(),
                marca.getIdMarca(),
                categoria.getIdCategoria()
            )
            .orElse(null);

        if (productoExistentePorDatos != null) {
            if (detalle.getPrecioUnitario() != null) {
                productoExistentePorDatos.setPrecioCompra(
                    detalle.getPrecioUnitario()
                );
                productoRepository.save(productoExistentePorDatos);
            }

            return productoExistentePorDatos;
        }

        Producto nuevoProducto = new Producto();

        nuevoProducto.setMarca(marca);
        nuevoProducto.setCategoria(categoria);
        nuevoProducto.setNombre(productoRecibido.getNombre());
        nuevoProducto.setDescripcion(productoRecibido.getDescripcion());
        nuevoProducto.setPrecioVenta(productoRecibido.getPrecioVenta());

        if (productoRecibido.getPrecioCompra() != null) {
            nuevoProducto.setPrecioCompra(productoRecibido.getPrecioCompra());
        } else if (detalle.getPrecioUnitario() != null) {
            nuevoProducto.setPrecioCompra(detalle.getPrecioUnitario());
        } else {
            throw new RuntimeException(
                "El producto nuevo debe tener precio de compra."
            );
        }

        if (
            productoRecibido.getEstado() == null ||
            productoRecibido.getEstado().isBlank()
        ) {
            nuevoProducto.setEstado("Habilitado");
        } else {
            nuevoProducto.setEstado(productoRecibido.getEstado());
        }

        return productoRepository.save(nuevoProducto);
    }

    private SucursalProducto crearStockSucursalProducto(
        Sucursal sucursal,
        Producto producto
    ) {
        SucursalProducto nuevoStock = new SucursalProducto();

        SucursalProductoId id = new SucursalProductoId(
            sucursal.getIdSucursal(),
            producto.getIdProducto()
        );

        nuevoStock.setId(id);
        nuevoStock.setSucursal(sucursal);
        nuevoStock.setProducto(producto);
        nuevoStock.setStock(0);
        nuevoStock.setStockMinimo(0);

        return nuevoStock;
    }

    @Override
    @Transactional(readOnly = true)
    public Compra getCompraById(Long id) {
        return compraRepository
            .findById(id)
            .orElseThrow(() ->
                new RuntimeException("Compra no encontrada con ID: " + id)
            );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> getComprasBySucursal(Long idSucursal) {
        return compraRepository.findBySucursal_IdSucursal(idSucursal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> getComprasByProveedor(Integer idProveedor) {
        return compraRepository.findByProveedor_IdProveedor(idProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> getComprasByAdministrador(Integer idAdministrador) {
        return compraRepository.findByAdministrador_IdAdministrador(
            idAdministrador
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> getComprasByEstado(String estado) {
        return compraRepository.findByEstado(estado);
    }

    @Override
    @Transactional
    public Compra updateCompra(Long id, Compra compra) {
        Compra compraExistente = getCompraById(id);

        compraExistente.setSucursal(compra.getSucursal());
        compraExistente.setProveedor(compra.getProveedor());
        compraExistente.setAdministrador(compra.getAdministrador());
        compraExistente.setUsuario(compra.getUsuario());
        compraExistente.setMetodoPago(compra.getMetodoPago());
        compraExistente.setFechaRegistro(compra.getFechaRegistro());
        compraExistente.setPeriodo(compra.getPeriodo());
        compraExistente.setTipoComprobante(compra.getTipoComprobante());
        compraExistente.setSerie(compra.getSerie());
        compraExistente.setNumeroSerie(compra.getNumeroSerie());
        compraExistente.setEstado(compra.getEstado());

        return compraRepository.save(compraExistente);
    }

    @Override
    @Transactional
    public Compra anularCompra(Long id) {
        Compra compra = getCompraById(id);

        if ("Anulada".equalsIgnoreCase(compra.getEstado())) {
            throw new RuntimeException("La compra ya está anulada.");
        }

        if (
            compra.getAdministrador() == null ||
            compra.getAdministrador().getUsuario() == null
        ) {
            throw new RuntimeException(
                "No se puede anular la compra porque no tiene administrador válido."
            );
        }

        for (CompraDetalle detalle : compra.getDetalles()) {
            SucursalProducto stockSucursal = sucursalProductoRepository
                .findBySucursal_IdSucursalAndProducto_IdProducto(
                    compra.getSucursal().getIdSucursal(),
                    detalle.getProducto().getIdProducto()
                )
                .orElseThrow(() ->
                    new RuntimeException(
                        "No existe stock para el producto " +
                            detalle.getProducto().getNombre()
                    )
                );

            if (stockSucursal.getStock() < detalle.getCantidad()) {
                throw new RuntimeException(
                    "No se puede anular la compra. Stock insuficiente para revertir producto: " +
                        detalle.getProducto().getNombre()
                );
            }

            stockSucursal.setStock(
                stockSucursal.getStock() - detalle.getCantidad()
            );

            sucursalProductoRepository.save(stockSucursal);

            MovimientoInventario movimiento = new MovimientoInventario();

            movimiento.setSucursal(compra.getSucursal());
            movimiento.setProducto(detalle.getProducto());
            movimiento.setUsuario(compra.getAdministrador().getUsuario());
            movimiento.setFecha(LocalDateTime.now());
            movimiento.setTipo("Salida");
            movimiento.setCantidad(detalle.getCantidad());
            movimiento.setMotivo(
                "Anulación de compra " +
                    compra.getSerie() +
                    "-" +
                    compra.getNumeroSerie()
            );

            movimientoInventarioRepository.save(movimiento);
        }

        compra.setEstado("Anulada");

        return compraRepository.save(compra);
    }

    @Override
    @Transactional
    public void deleteCompra(Long id) {
        Compra compra = getCompraById(id);

        compraRepository.delete(compra);
    }

    private void validarCompra(Compra compra) {
        if (compra == null) {
            throw new RuntimeException("La compra no puede ser nula.");
        }

        if (
            compra.getSucursal() == null ||
            compra.getSucursal().getIdSucursal() == null
        ) {
            throw new RuntimeException("La compra debe tener una sucursal.");
        }

        if (
            compra.getProveedor() == null ||
            compra.getProveedor().getIdProveedor() == null
        ) {
            throw new RuntimeException("La compra debe tener un proveedor.");
        }

        if (
            compra.getAdministrador() == null ||
            compra.getAdministrador().getIdAdministrador() == null
        ) {
            throw new RuntimeException(
                "La compra debe ser registrada por un administrador."
            );
        }

        if (
            compra.getMetodoPago() == null ||
            compra.getMetodoPago().getIdMetodoPago() == null
        ) {
            throw new RuntimeException(
                "La compra debe tener un método de pago."
            );
        }

        if (
            compra.getTipoComprobante() == null ||
            compra.getTipoComprobante().isBlank()
        ) {
            throw new RuntimeException(
                "La compra debe tener tipo de comprobante."
            );
        }

        if (compra.getSerie() == null || compra.getSerie().isBlank()) {
            throw new RuntimeException("La compra debe tener serie.");
        }

        if (
            compra.getNumeroSerie() == null || compra.getNumeroSerie().isBlank()
        ) {
            throw new RuntimeException("La compra debe tener número de serie.");
        }

        if (compra.getDetalles() == null || compra.getDetalles().isEmpty()) {
            throw new RuntimeException(
                "La compra debe tener al menos un detalle."
            );
        }
    }

    private void validarDetalle(CompraDetalle detalle) {
        if (detalle == null) {
            throw new RuntimeException(
                "El detalle de compra no puede ser nulo."
            );
        }

        if (detalle.getProducto() == null) {
            throw new RuntimeException("Cada detalle debe tener un producto.");
        }

        if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
            throw new RuntimeException(
                "La cantidad del producto debe ser mayor que cero."
            );
        }
    }

    private void validarProductoNuevo(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new RuntimeException("El producto nuevo debe tener nombre.");
        }

        if (
            producto.getMarca() == null ||
            producto.getMarca().getIdMarca() == null
        ) {
            throw new RuntimeException("El producto nuevo debe tener marca.");
        }

        if (
            producto.getCategoria() == null ||
            producto.getCategoria().getIdCategoria() == null
        ) {
            throw new RuntimeException(
                "El producto nuevo debe tener categoría."
            );
        }

        if (producto.getPrecioVenta() == null) {
            throw new RuntimeException(
                "El producto nuevo debe tener precio de venta."
            );
        }
    }
}
