package com.example.papmatricula.feing;


import com.example.papmatricula.dto.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "pap-cursos",
        url = "http://localhost:8081", // Cambia el puerto si tu ms-cursos usa otro
        fallbackFactory = CursoClientFallbackFactory.class
)
public interface CursoClient {

    @GetMapping("/cursos/{id}")
    Curso getCursoById(@PathVariable Long id);

    @PutMapping("/cursos")
    void actualizarCurso(@RequestBody Curso curso);
}


