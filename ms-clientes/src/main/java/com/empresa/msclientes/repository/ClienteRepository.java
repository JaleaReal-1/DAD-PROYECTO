package com.empresa.msclientes.repository;

import com.empresa.msclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
