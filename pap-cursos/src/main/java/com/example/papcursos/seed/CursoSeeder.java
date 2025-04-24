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
            // Crear los cursos con la información proporcionada
            Curso c1 = new Curso(null, "Programación I", "Lunes y Miércoles 8:00am", 30, "SIS101", "1er", 0);
            Curso c2 = new Curso(null, "Matemática Básica", "Martes y Jueves 10:00am", 40, "MAT201", "1er", 0);
            Curso c3 = new Curso(null, "Estructuras de Datos", "Viernes 2:00pm", 25, "SIS301", "3er", 0);
            Curso c4 = new Curso(null, "Redes de Computadoras", "Lunes y Miércoles 2:00pm", 35, "SIS201", "2do", 0);
            Curso c5 = new Curso(null, "Algoritmos y Complejidad", "Martes y Jueves 8:00am", 30, "SIS401", "4to", 0);

            // Verificar si ya existen cursos con las mismas claves para evitar duplicados
            if (!repository.existsByCodigo("SIS101")) {
                repository.save(c1);
            }
            if (!repository.existsByCodigo("MAT201")) {
                repository.save(c2);
            }
            if (!repository.existsByCodigo("SIS301")) {
                repository.save(c3);
            }
            if (!repository.existsByCodigo("SIS201")) {
                repository.save(c4);
            }
            if (!repository.existsByCodigo("SIS401")) {
                repository.save(c5);
            }

            System.out.println(">>> Cursos insertados correctamente");
        } else {
            System.out.println(">>> Los cursos ya existen en la base de datos");
        }
    }
}
