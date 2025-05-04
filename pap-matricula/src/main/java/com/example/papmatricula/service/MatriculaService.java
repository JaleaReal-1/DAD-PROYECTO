package com.example.papmatricula.service;

import com.example.papmatricula.feing.CursoClient;
import com.example.papmatricula.feing.EstudianteClient;
import com.example.papmatricula.dto.Curso;
import com.example.papmatricula.dto.Estudiante;
import com.example.papmatricula.dto.MatriculaResponse;
import com.example.papmatricula.entity.Matricula;
import com.example.papmatricula.repository.MatriculaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Estudiante estudiante = obtenerEstudiantePorId(m.getEstudianteId());
        if (!"Activo".equalsIgnoreCase(estudiante.getEstado())) {
            throw new RuntimeException("El estudiante no está activo");
        }

        Curso curso = obtenerCursoPorId(m.getCursoId());
        if (curso.getInscritos() >= curso.getCapacidad()) {
            throw new RuntimeException("El curso ya alcanzó su capacidad máxima");
        }

        boolean yaMatriculado = repository.existsByEstudianteIdAndCursoId(m.getEstudianteId(), m.getCursoId());
        if (yaMatriculado) {
            throw new RuntimeException("El estudiante ya está matriculado en este curso");
        }

        if (m.getFecha() == null) {
            m.setFecha(LocalDate.now());
        }

        curso.setCapacidad(curso.getCapacidad() - 1);
        cursoClient.actualizarCurso(curso);

        return repository.save(m);
    }

    public MatriculaResponse buscarPorId(Long id) {
        Matricula matricula = repository.findById(id).orElse(null);
        if (matricula == null) return null;

        Estudiante estudiante = obtenerEstudiantePorId(matricula.getEstudianteId());
        Curso curso = obtenerCursoPorId(matricula.getCursoId());

        MatriculaResponse response = new MatriculaResponse();
        response.setId(matricula.getId());
        response.setEstudiante(estudiante);
        response.setCurso(curso);
        response.setFecha(matricula.getFecha());
        response.setRegistroCiclo(matricula.getRegistroCiclo());

        return response;
    }

    // Circuit Breaker para obtener estudiante
    @CircuitBreaker(name = "estudianteCB", fallbackMethod = "fallbackEstudiante")
    public Estudiante obtenerEstudiantePorId(Long id) {
        return estudianteClient.getEstudianteById(id);
    }

    // Circuit Breaker para obtener curso
    @CircuitBreaker(name = "cursoCB", fallbackMethod = "fallbackCurso")
    public Curso obtenerCursoPorId(Long id) {
        return cursoClient.getCursoById(id);
    }

    // FallBack correcto: misma firma + excepción
    public Estudiante fallbackEstudiante(Long id, Throwable t) {
        Estudiante est = new Estudiante();
        est.setId(id);
        est.setNombre("ERROR: Estudiante no disponible");
        est.setEstado("Inactivo");
        return est;
    }

    public Curso fallbackCurso(Long id, Throwable t) {
        Curso curso = new Curso();
        curso.setId(id);
        curso.setCurso("ERROR: Curso no disponible");
        curso.setCapacidad(0);
        curso.setInscritos(0);
        return curso;
    }

    public boolean existeMatriculaPorEstudianteYCurso(Long estudianteId, Long cursoId) {
        return repository.existsByEstudianteIdAndCursoId(estudianteId, cursoId);
    }

    public boolean capacidadCompleta(Long cursoId) {
        Curso curso = obtenerCursoPorId(cursoId);
        return curso.getInscritos() >= curso.getCapacidad();
    }
}
