package com.example.papmatricula.feing;


import com.example.papmatricula.dto.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pap-cursos")
public interface CursoClient {

    @GetMapping("/cursos/{id}")
    Curso getCursoById(@PathVariable Long id);

    @PutMapping("/cursos")
    ResponseEntity<Curso> actualizarCurso(@RequestBody Curso curso);  // Ya está bien para actualizar el curso
}

