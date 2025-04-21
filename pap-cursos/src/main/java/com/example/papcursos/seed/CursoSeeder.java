package com.example.papcursos.seed;


import com.example.papcursos.entity.Curso;
import com.example.papcursos.repository.CursoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CursoSeeder {

    private final CursoRepository repository;

    @PostConstruct
    public void run() {
        if (repository.count() == 0) {
            Curso c1 = new Curso(null, "Programación I", "Lunes y Miércoles 8:00am", 30, "SIS101", "1er");
            Curso c2 = new Curso(null, "Matemática Básica", "Martes y Jueves 10:00am", 40, "MAT201", "1er");
            Curso c3 = new Curso(null, "Estructuras de Datos", "Viernes 2:00pm", 25, "SIS301", "3er");

            repository.saveAll(List.of(c1, c2, c3));
            System.out.println(">>> Cursos insertados correctamente");
        }
    }
}
