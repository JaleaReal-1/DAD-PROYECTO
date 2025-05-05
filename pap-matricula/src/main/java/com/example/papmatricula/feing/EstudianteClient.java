package com.example.papmatricula.feing;



import com.example.papmatricula.dto.Estudiante;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pap-estudiantes", path = "/estudiantes", fallbackFactory = EstudianteClientFallbackFactory.class)
public interface EstudianteClient {
    @GetMapping("/{id}")
    Estudiante getEstudianteById(@PathVariable Long id);
}


