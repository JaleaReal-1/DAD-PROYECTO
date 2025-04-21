package com.example.papmatricula.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class MatriculaResponse {
    private Long id;
    private Estudiante estudiante;
    private Curso curso;
    private String registroCiclo;
    private LocalDate fecha;
}
