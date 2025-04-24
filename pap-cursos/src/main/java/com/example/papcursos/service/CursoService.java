package com.example.papcursos.service;

import com.example.papcursos.entity.Curso;
import com.example.papcursos.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository repository;

    public List<Curso> listar() {
        return repository.findAll();
    }

    public Curso guardar(Curso curso) {
        curso.setInscritos(0); // Por defecto inicia con 0
        return repository.save(curso);
    }

    public boolean hayCuposDisponibles(Long id) {
        Curso curso = repository.findById(id).orElseThrow();
        return curso.getInscritos() < curso.getCapacidad();
    }

    public void aumentarInscritos(Long id) {
        Curso curso = repository.findById(id).orElseThrow();
        if (curso.getInscritos() < curso.getCapacidad()) {
            curso.setInscritos(curso.getInscritos() + 1);
            repository.save(curso);
        } else {
            throw new RuntimeException("No hay cupos disponibles en este curso");
        }
    }

    public Curso obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Curso actualizar(Curso curso) {
        return repository.save(curso);  // Esto guarda los cambios en la base de datos
    }
}
