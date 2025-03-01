package com.mangosoft.MMedManager.model.services;

import java.util.List;
import java.util.Optional;

import com.mangosoft.MMedManager.model.entities.AreaMedica;

public interface iAreaMedicaService {

    List<AreaMedica> buscarTodos();

    public Optional<AreaMedica> buscarPorId(Long id);

    void guardar(AreaMedica areaMedica);

    void borrarPorId(Long id);

}
