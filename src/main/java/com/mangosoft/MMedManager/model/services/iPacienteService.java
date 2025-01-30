package com.mangosoft.MMedManager.model.services;

import java.util.List;
import java.util.Optional;

import com.mangosoft.MMedManager.model.entities.Paciente;

public interface iPacienteService {

    List<Paciente> buscarTodos();

    public Optional<Paciente> buscarPorId(Long id);

    void guardar(Paciente paciente);

    void borrarPorId(Long id);

}
