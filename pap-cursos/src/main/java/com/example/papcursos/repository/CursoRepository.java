package com.example.papcursos.repository;



import com.example.papcursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByCodigo(String codigo);  // Verifica si un curso ya existe por su código
}
