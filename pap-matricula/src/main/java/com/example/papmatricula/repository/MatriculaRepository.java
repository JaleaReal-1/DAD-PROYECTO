package com.example.papmatricula.repository;

import com.example.papmatricula.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    long countByCursoId(Long cursoId);

    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
}
