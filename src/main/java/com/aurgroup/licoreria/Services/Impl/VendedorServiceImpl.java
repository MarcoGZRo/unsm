package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.Vendedor;
import com.aurgroup.licoreria.Repositories.VendedorRepository;
import com.aurgroup.licoreria.Services.VendedorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorServiceImpl(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    @Transactional
    public Vendedor createVendedor(Vendedor vendedor) {
        if (vendedor.getEstado() == null || vendedor.getEstado().isBlank()) {
            vendedor.setEstado("activo");
        }

        return vendedorRepository.save(vendedor);
    }

    @Override
    @Transactional(readOnly = true)
    public Vendedor getVendedorById(Integer id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vendedor> getAllVendedores() {
        return vendedorRepository.findAll();
    }

    @Override
    @Transactional
    public Vendedor updateVendedor(Integer id, Vendedor vendedor) {
        getVendedorById(id);

        vendedor.setIdVendedor(id);

        return vendedorRepository.save(vendedor);
    }

    @Override
    @Transactional
    public void deleteVendedor(Integer id) {
        if (!vendedorRepository.existsById(id)) {
            throw new RuntimeException("Vendedor no encontrado con ID: " + id);
        }

        vendedorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Vendedor desactivarVendedor(Integer id) {
        Vendedor vendedor = getVendedorById(id);

        vendedor.setEstado("inactivo");

        return vendedorRepository.save(vendedor);
    }
}