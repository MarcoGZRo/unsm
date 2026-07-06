package com.aurgroup.licoreria.Services;

import com.aurgroup.licoreria.Models.Cliente;

/**
 * ClienteService
 * @author M4riotaku
 */

public interface ClienteService {
    Cliente createCliente(Cliente cliente);
    Cliente updateCliente(Cliente cliente);
    void deleteCliente(Long id);
    Cliente getClienteById(Long id);
}
