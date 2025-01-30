package com.mangosoft.MMedManager.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Paciente;
import com.mangosoft.MMedManager.model.repository.iPacienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PacienteServiceImpl implements iPacienteService {

    @Autowired
    private iPacienteRepository pacienteRepo;

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepo.findAll();
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepo.findById(id);
    }

    public void guardar(Paciente paciente) {
        if (paciente.getId() != null) {
            Optional<Paciente> pacienteExistente = pacienteRepo.findById(paciente.getId());
            if (pacienteExistente.isPresent()) {
                pacienteRepo.save(paciente); // Actualización
            } else {
                throw new EntityNotFoundException("El paciente no existe.");
            }
        } else {
            pacienteRepo.save(paciente); // Creación de nuevo paciente
        }
    }

    @Override
    public void borrarPorId(Long id) {
        pacienteRepo.deleteById(id);
    }
}
