package com.example.papmatricula.controller;

import com.example.papmatricula.dto.MatriculaResponse;
import com.example.papmatricula.entity.Matricula;
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

    // ✅ Devuelve lista de MatriculaResponse
    @GetMapping
    public List<MatriculaResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody Matricula m) {
        try {
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
