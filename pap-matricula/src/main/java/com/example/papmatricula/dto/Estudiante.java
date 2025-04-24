package com.example.papmatricula.dto;

import lombok.Data;

@Data
public class Estudiante {
    private Long id;
    private String nombre;
    private String carrera;
    private String estado;
    private String cicloActual;
    private String dni; // 👈 Campo agregado
}
