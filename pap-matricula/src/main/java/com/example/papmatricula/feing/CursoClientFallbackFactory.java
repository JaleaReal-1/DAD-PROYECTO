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
                Curso fallbackCurso = new Curso();
                fallbackCurso.setId(id);
                fallbackCurso.setCurso("ERROR: Curso no disponible");
                fallbackCurso.setCapacidad(0);
                fallbackCurso.setInscritos(0);
                return fallbackCurso;
            }

            @Override
            public void actualizarCurso(Curso curso) {
                System.out.println("No se pudo actualizar el curso. Fallback activado. Causa: " + cause.getMessage());
            }
        };
    }
}
