package com.example.papestudiantes.service;

import com.example.papestudiantes.entity.Estudiante;
import com.example.papestudiantes.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository repository;

    public List<Estudiante> listarTodos() {
        return repository.findAll();
    }

    public Estudiante buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public Estudiante guardar(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public Estudiante actualizar(Long id, Estudiante estudiante) {
        Estudiante encontrado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        encontrado.setNombre(estudiante.getNombre());
        encontrado.setCarrera(estudiante.getCarrera());
        encontrado.setEstado(estudiante.getEstado());
        encontrado.setCicloActual(estudiante.getCicloActual());
        encontrado.setDni(estudiante.getDni()); // 👈 Se añade esta línea

        return repository.save(encontrado);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
