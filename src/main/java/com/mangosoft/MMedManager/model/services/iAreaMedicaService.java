package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.AreaMedica;

public interface iAreaMedicaService {

    List<AreaMedica> buscarTodos();

    AreaMedica buscarPorId(Long id);

    void guardar(AreaMedica areaMedica);

    void borrarPorId(Long id);

}
