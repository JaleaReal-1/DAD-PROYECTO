package com.example.papmatricula.feing;

import com.example.papmatricula.dto.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "pap-cursos")
public interface CursoClient {

    @GetMapping("/cursos/{id}")
    Curso getCursoById(@PathVariable Long id);

    @PutMapping("/cursos")
    void actualizarCurso(@RequestBody Curso curso);
}
