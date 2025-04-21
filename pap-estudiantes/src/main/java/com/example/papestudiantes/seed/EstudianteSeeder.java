package com.example.papestudiantes.seed;

import com.example.papestudiantes.entity.Estudiante;
import com.example.papestudiantes.repository.EstudianteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EstudianteSeeder {

    private final EstudianteRepository repository;

    @PostConstruct
    public void run() {
        if (repository.count() == 0) {
            Estudiante e1 = new Estudiante(null, "Carlos Pérez", "Ingeniería de Sistemas", "Activo", "5to");
            Estudiante e2 = new Estudiante(null, "Ana Torres", "Arquitectura", "Inactivo", "3ro");
            Estudiante e3 = new Estudiante(null, "Luis Gutiérrez", "Medicina", "Activo", "7mo");

            repository.saveAll(List.of(e1, e2, e3));
            System.out.println(">>> Estudiantes insertados correctamente");
        } else {
            System.out.println(">>> Ya existen estudiantes en la base de datos");
        }
    }
}
