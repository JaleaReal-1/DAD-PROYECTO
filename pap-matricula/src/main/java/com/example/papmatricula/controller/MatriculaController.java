package com.example.papmatricula.controller;

import com.example.papmatricula.dto.Estudiante;
import com.example.papmatricula.dto.MatriculaResponse;
import com.example.papmatricula.entity.Matricula;
import com.example.papmatricula.feing.EstudianteClient;
import com.example.papmatricula.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService service;
    private final EstudianteClient estudianteClient;

    @GetMapping
    public List<Matricula> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody Matricula m) {
        try {
            // Obtener el estudiante por su ID
            Estudiante estudiante = estudianteClient.getEstudianteById(m.getEstudianteId());

            // Verificación si el estudiante está activo
            if (estudiante == null || "Inactivo".equalsIgnoreCase(estudiante.getEstado())) {
                return new ResponseEntity<>("El estudiante está inactivo, no puede matricularse.", HttpStatus.BAD_REQUEST);
            }

            // Verificación si el estudiante ya está matriculado en el curso
            if (service.existeMatriculaPorEstudianteYCurso(m.getEstudianteId(), m.getCursoId())) {
                return new ResponseEntity<>("El estudiante ya está matriculado en este curso.", HttpStatus.BAD_REQUEST);
            }

            // Verificación de capacidad del curso
            if (service.capacidadCompleta(m.getCursoId())) {
                return new ResponseEntity<>("El curso ya ha alcanzado su capacidad máxima.", HttpStatus.BAD_REQUEST);
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
}
