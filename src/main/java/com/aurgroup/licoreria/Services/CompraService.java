package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Compra;

/**
 * CompraService
 * @author M4riotaku
 */

public interface CompraService {
    Compra createCompra(Compra compra);
    Compra updateCompra(Compra compra);
    void deleteCompra(Long id);
    Compra getCompraById(Long id);
}
