package com.example.papmatricula.feing;

import com.example.papmatricula.dto.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "pap-cursos",
        url = "http://localhost:8081",
        fallback = CursoClient.Fallback.class  // Agregamos el fallback directamente
)
public interface CursoClient {

    @GetMapping("/cursos/{id}")
    Curso getCursoById(@PathVariable Long id);

    @PutMapping("/cursos")
    void actualizarCurso(@RequestBody Curso curso);

    // Definimos un método fallback directamente dentro de la interfaz
    class Fallback implements CursoClient {

        @Override
        public Curso getCursoById(Long id) {
            // Retornamos un curso con valores predeterminados si la llamada falla
            Curso cursoFallback = new Curso();
            cursoFallback.setId(id);
            cursoFallback.setCurso("Curso no disponible");
            return cursoFallback;
        }

        @Override
        public void actualizarCurso(Curso curso) {
            // Lógica para manejar la falla al actualizar el curso
            System.out.println("No se puede actualizar el curso, servicio de cursos no disponible.");
        }
    }
}
