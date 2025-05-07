package com.example.papmatricula.feing;

import com.example.papmatricula.dto.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "pap-cursos",
        url = "http://localhost:8081",
        fallback = CursoClient.Fallback.class  // Aquí ya estás usando el fallback directamente
)
public interface CursoClient {

    @GetMapping("/cursos/{id}")
    Curso getCursoById(@PathVariable Long id);

    @PutMapping("/cursos")
    void actualizarCurso(@RequestBody Curso curso);

    // Fallback implementado directamente aquí
    class Fallback implements CursoClient {

        @Override
        public Curso getCursoById(Long id) {
            // Devuelvo un curso con valores predeterminados para que el sistema no falle
            Curso cursoFallback = new Curso();
            cursoFallback.setId(id);
            cursoFallback.setCurso("ERROR: Curso no disponible");
            cursoFallback.setHorario("No disponible");
            cursoFallback.setCapacidad(0);
            cursoFallback.setCodigo("ERROR");
            cursoFallback.setCiclo("No disponible");
            cursoFallback.setInscritos(0);
            return cursoFallback;
        }

        @Override
        public void actualizarCurso(Curso curso) {
            // Lógica del fallback para actualizar
            System.out.println("No se puede actualizar el curso, servicio de cursos no disponible.");
        }
    }
    @Component
    class CursoClientFallback implements CursoClient {



        @Override
        public Curso getCursoById(Long id) {
            return null;
        }

        @Override
        public void actualizarCurso(Curso curso) {

        }
    }
}

