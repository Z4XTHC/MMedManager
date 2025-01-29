package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.Paciente;

public interface iPacienteService {

    List<Paciente> buscarTodos();

    Paciente buscarPorId(Long id);

    void guardar(Paciente paciente);

    void borrarPorId(Long id);

}
