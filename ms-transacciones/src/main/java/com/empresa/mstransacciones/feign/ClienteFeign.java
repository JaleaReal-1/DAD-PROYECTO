package com.empresa.mstransacciones.feign;

import com.empresa.mstransacciones.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-clientes", url = "http://localhost:8082")
public interface ClienteFeign {
    @GetMapping("/clientes/{id}")
    ClienteDTO obtenerClientePorId(@PathVariable("id") Long id);
}

