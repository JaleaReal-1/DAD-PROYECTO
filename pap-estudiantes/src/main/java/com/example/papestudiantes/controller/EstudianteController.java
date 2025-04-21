package com.example.papestudiantes.controller;

import com.example.papestudiantes.entity.Estudiante;
import com.example.papestudiantes.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService service;

    @GetMapping
    public List<Estudiante> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return service.guardar(estudiante);
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        return service.actualizar(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
