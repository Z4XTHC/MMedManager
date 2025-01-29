package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Paciente;
import com.mangosoft.MMedManager.model.repository.iPacienteRepository;

@Service
public class PacienteServiceImpl implements iPacienteService {

    @Autowired
    private iPacienteRepository pacienteRepo;

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepo.findAll();
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return pacienteRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Paciente paciente) {
        pacienteRepo.save(paciente);
    }

    @Override
    public void borrarPorId(Long id) {
        pacienteRepo.deleteById(id);
    }
}
