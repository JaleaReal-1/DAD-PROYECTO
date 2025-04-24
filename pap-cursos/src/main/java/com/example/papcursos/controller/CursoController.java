package com.example.papcursos.controller;

import com.example.papcursos.entity.Curso;
import com.example.papcursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService service;

    @GetMapping
    public List<Curso> listar() {
        return service.listar();
    }

    @PostMapping
    public Curso guardar(@RequestBody Curso curso) {
        return service.guardar(curso);
    }

    @GetMapping("/{id}/cupos")
    public boolean hayCuposDisponibles(@PathVariable Long id) {
        return service.hayCuposDisponibles(id);
    }

    @PutMapping("/{id}/inscribir")
    public void aumentarInscritos(@PathVariable Long id) {
        service.aumentarInscritos(id);
    }

    @GetMapping("/{id}")
    public Curso obtenerCursoPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping
    public ResponseEntity<Curso> actualizarCurso(@RequestBody Curso curso) {
        try {
            Curso cursoActualizado = service.actualizar(curso); // Este método en servicio debe guardar el curso con la capacidad actualizada
            return ResponseEntity.ok(cursoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }



}
