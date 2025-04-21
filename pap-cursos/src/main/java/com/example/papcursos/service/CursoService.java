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
        return repository.save(curso);
    }
}
