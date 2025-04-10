package com.empresa.msclientes.service;

import com.empresa.msclientes.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarClientes();
    Optional<Cliente> obtenerClientePorId(Long id);
    Cliente crearCliente(Cliente cliente);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}
