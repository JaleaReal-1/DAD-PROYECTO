package com.example.papestudiantes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String carrera;
    private String estado;
    private String cicloActual;
    @Column(unique = true) // Asegura que el DNI sea único
    private String dni; // Nuevo campo agregado
}
