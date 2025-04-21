package com.example.papmatricula.service;


import com.example.papmatricula.feing.CursoClient;
import com.example.papmatricula.feing.EstudianteClient;
import com.example.papmatricula.dto.Curso;
import com.example.papmatricula.dto.Estudiante;
import com.example.papmatricula.dto.MatriculaResponse;
import com.example.papmatricula.entity.Matricula;
import com.example.papmatricula.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository repository;
    private final EstudianteClient estudianteClient;
    private final CursoClient cursoClient;

    public List<Matricula> listar() {
        return repository.findAll();
    }

    public Matricula guardar(Matricula m) {
        return repository.save(m);
    }

    public MatriculaResponse buscarPorId(Long id) {
        Matricula matricula = repository.findById(id).orElse(null);
        if (matricula == null) return null;

        Estudiante estudiante = estudianteClient.getEstudianteById(matricula.getEstudianteId());
        Curso curso = cursoClient.getCursoById(matricula.getCursoId());

        MatriculaResponse response = new MatriculaResponse();
        response.setId(matricula.getId());
        response.setEstudiante(estudiante);
        response.setCurso(curso);
        response.setFecha(matricula.getFecha());
        response.setRegistroCiclo(matricula.getRegistroCiclo());

        return response;
    }
}
