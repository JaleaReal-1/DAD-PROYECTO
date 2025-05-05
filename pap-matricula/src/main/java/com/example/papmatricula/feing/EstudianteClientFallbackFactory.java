package com.example.papmatricula.feing;

import com.example.papmatricula.dto.Estudiante;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EstudianteClientFallbackFactory implements FallbackFactory<EstudianteClient> {
    @Override
    public EstudianteClient create(Throwable cause) {
        return new EstudianteClient() {
            @Override
            public Estudiante getEstudianteById(Long id) {
                Estudiante est = new Estudiante();
                est.setId(id);
                est.setNombre("ERROR: Estudiante no disponible");
                est.setEstado("Inactivo");
                return est;
            }
        };
    }
}
