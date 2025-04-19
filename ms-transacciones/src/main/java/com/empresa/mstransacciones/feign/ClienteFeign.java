package com.empresa.mstransacciones.feign;

import com.empresa.mstransacciones.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-clientes", path = "/clientes")
public interface ClienteFeign {
    @GetMapping("/{id}")
    ClienteDTO obtenerClientePorId(@PathVariable("id") Long id);
}
