package com.aurgroup.licoreria.Services.Impl;

import com.aurgroup.licoreria.Models.Cliente;
import com.aurgroup.licoreria.Repositories.ClienteRepository;
import com.aurgroup.licoreria.Services.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClienteServiceImpl
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        Cliente existingCliente = clienteRepository
            .findById(cliente.getIdCliente())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        existingCliente.setTipoCliente(cliente.getTipoCliente());
        existingCliente.setTipoDocumento(cliente.getTipoDocumento());
        existingCliente.setNombres(cliente.getNombres());
        existingCliente.setApellidos(cliente.getApellidos());
        existingCliente.setRazonSocial(cliente.getRazonSocial());
        existingCliente.setTelefono(cliente.getTelefono());
        existingCliente.setDireccion(cliente.getDireccion());
        existingCliente.setCorreo(cliente.getCorreo());
        existingCliente.setEstado(cliente.getEstado());
        existingCliente.setNumeroDocumento(cliente.getNumeroDocumento());

        return clienteRepository.save(existingCliente);
    }

    @Override
    public void deleteCliente(Long id) {
        Cliente existingCliente = clienteRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clienteRepository.delete(existingCliente);
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}
