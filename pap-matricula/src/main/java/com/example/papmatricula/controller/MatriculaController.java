package com.example.papmatricula.controller;

import com.example.papmatricula.dto.Curso;
import com.example.papmatricula.dto.MatriculaResponse;
import com.example.papmatricula.entity.Matricula;
import com.example.papmatricula.feing.CursoClient;
import com.example.papmatricula.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;  // Importamos la anotación

import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService service;
    private final CursoClient cursoClient;  // Cliente Feign para cursos

    @GetMapping
    public List<Matricula> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody Matricula m) {
        try {
            // Obtener el curso por su ID, con Circuit Breaker
            Curso curso = obtenerCursoPorId(m.getCursoId());

            // Verificación si el curso existe
            if (curso == null) {
                return new ResponseEntity<>("El curso no existe.", HttpStatus.BAD_REQUEST);
            }

            // Verificación si el curso ya ha alcanzado su capacidad máxima
            if (service.capacidadCompleta(m.getCursoId())) {
                return new ResponseEntity<>("El curso ya ha alcanzado su capacidad máxima.", HttpStatus.BAD_REQUEST);
            }

            // Verificación si el estudiante ya está matriculado en el curso
            if (service.existeMatriculaPorEstudianteYCurso(m.getEstudianteId(), m.getCursoId())) {
                return new ResponseEntity<>("El estudiante ya está matriculado en este curso.", HttpStatus.BAD_REQUEST);
            }

            // Guardar matrícula y disminuir capacidad del curso
            Matricula matriculaGuardada = service.guardar(m);

            return new ResponseEntity<>("Matrícula registrada correctamente con ID: " + matriculaGuardada.getId(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar la matrícula: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public MatriculaResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Método con Circuit Breaker para obtener el curso
    @CircuitBreaker(name = "papCursosCB", fallbackMethod = "fallbackCurso")
    public Curso obtenerCursoPorId(Long id) {
        return cursoClient.getCursoById(id);  // Llamada al servicio Feign para obtener el curso
    }

    // Método Fallback en caso de que el servicio de cursos falle
    public Curso fallbackCurso(Long id, Throwable e) {
        // Retornamos un curso con valores predeterminados si la llamada falla
        Curso cursoFallback = new Curso();
        cursoFallback.setId(id);
        cursoFallback.setCurso("Curso no disponible");
        return cursoFallback;
    }
}
