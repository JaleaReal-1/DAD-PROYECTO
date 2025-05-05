package com.example.papmatricula.feing;

import com.example.papmatricula.dto.Curso;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CursoClientFallbackFactory implements FallbackFactory<CursoClient> {
    @Override
    public CursoClient create(Throwable cause) {
        return new CursoClient() {
            @Override
            public Curso getCursoById(Long id) {
                Curso curso = new Curso();
                curso.setId(id);
                curso.setCurso("ERROR: Curso no disponible");
                curso.setCapacidad(0);
                curso.setInscritos(0);
                return curso;
            }

            @Override
            public void actualizarCurso(Curso curso) {
                System.out.println("No se pudo actualizar el curso debido a un error: " + cause.getMessage());
            }
        };
    }
}

