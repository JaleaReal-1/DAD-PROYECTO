package com.example.papmatricula.dto;


import lombok.Data;

@Data
public class Curso {
    private Long id;
    private String curso;
    private String horario;
    private int capacidad;
    private String codigo;
    private String ciclo;
}
