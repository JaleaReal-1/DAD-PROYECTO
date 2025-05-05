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
        // Obtener estudiante con fallback manual
        Estudiante estudiante;
        try {
            estudiante = estudianteClient.getEstudianteById(m.getEstudianteId());
        } catch (Exception e) {
            estudiante = fallbackEstudiante(m.getEstudianteId(), e);
        }

        if ("ERROR: Estudiante no disponible".equalsIgnoreCase(estudiante.getNombre())) {
            throw new RuntimeException("No se pudo obtener información del estudiante (servicio no disponible)");
        }

        if (!"Activo".equalsIgnoreCase(estudiante.getEstado())) {
            throw new RuntimeException("El estudiante no está activo");
        }

        // Obtener curso con circuit breaker + fallback automático
        Curso curso = obtenerCursoConCircuitBreaker(m.getCursoId());
        System.out.println(">>> CURSO OBTENIDO: " + curso.getCurso());

        if ("ERROR: Curso no disponible".equalsIgnoreCase(curso.getCurso())) {
            throw new RuntimeException("No se pudo obtener información del curso (servicio no disponible)");
        }

        if (curso.getInscritos() >= curso.getCapacidad()) {
            throw new RuntimeException("El curso ya alcanzó su capacidad máxima");
        }

        // Validar si ya está matriculado
        boolean yaMatriculado = repository.existsByEstudianteIdAndCursoId(m.getEstudianteId(), m.getCursoId());
        if (yaMatriculado) {
            throw new RuntimeException("El estudiante ya está matriculado en este curso");
        }

        if (m.getFecha() == null) {
            m.setFecha(LocalDate.now());
        }

        // Simular actualización del curso
        try {
            curso.setCapacidad(curso.getCapacidad() - 1);
            cursoClient.actualizarCurso(curso);
        } catch (Exception e) {
            System.out.println("No se pudo actualizar el curso: " + e.getMessage());
        }

        return repository.save(m);
    }

    public MatriculaResponse buscarPorId(Long id) {
        Matricula matricula = repository.findById(id).orElse(null);
        if (matricula == null) return null;

        Estudiante estudiante;
        try {
            estudiante = estudianteClient.getEstudianteById(matricula.getEstudianteId());
        } catch (Exception e) {
            estudiante = fallbackEstudiante(matricula.getEstudianteId(), e);
        }

        Curso curso = obtenerCursoConCircuitBreaker(matricula.getCursoId());

        MatriculaResponse response = new MatriculaResponse();
        response.setId(matricula.getId());
        response.setEstudiante(estudiante);
        response.setCurso(curso);
        response.setFecha(matricula.getFecha());
        response.setRegistroCiclo(matricula.getRegistroCiclo());

        return response;
    }

    public boolean existeMatriculaPorEstudianteYCurso(Long estudianteId, Long cursoId) {
        return repository.existsByEstudianteIdAndCursoId(estudianteId, cursoId);
    }

    public boolean capacidadCompleta(Long cursoId) {
        Curso curso = obtenerCursoConCircuitBreaker(cursoId);
        if ("ERROR: Curso no disponible".equalsIgnoreCase(curso.getCurso())) {
            return true;
        }
        return curso.getInscritos() >= curso.getCapacidad();
    }

    // --- MÉTODOS DE FALLBACK ---

    // Fallback manual para estudiante
    public Estudiante fallbackEstudiante(Long id, Throwable t) {
        Estudiante est = new Estudiante();
        est.setId(id);
        est.setNombre("ERROR: Estudiante no disponible");
        est.setEstado("Inactivo");
        return est;
    }

    // Circuit breaker + fallback para curso
    @CircuitBreaker(name = "cursoClient", fallbackMethod = "fallbackCurso")
    public Curso obtenerCursoConCircuitBreaker(Long cursoId) {
        return cursoClient.getCursoById(cursoId);
    }

    // Fallback automático invocado por Resilience4j
    public Curso fallbackCurso(Long id, Throwable t) {
        System.out.println(">>> Fallback activado para curso ID " + id + ": " + t.getMessage());
        Curso curso = new Curso();
        curso.setId(id);
        curso.setCurso("ERROR: Curso no disponible");
        curso.setCapacidad(0);
        curso.setInscritos(0);
        return curso;
    }
}
