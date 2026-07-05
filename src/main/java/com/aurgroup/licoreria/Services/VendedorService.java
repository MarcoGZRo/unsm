package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Vendedor;
import java.util.List;
/**
 * AuthService
 * @author MarcoGZRo
 */
public interface VendedorService {

    Vendedor createVendedor(Vendedor vendedor);

    Vendedor getVendedorById(Integer id);

    List<Vendedor> getAllVendedores();

    Vendedor updateVendedor(Integer id, Vendedor vendedor);

    void deleteVendedor(Integer id);

    Vendedor desactivarVendedor(Integer id);
}
