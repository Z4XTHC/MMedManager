package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.FichaMedica;

public interface iFichaMedicaService {

    List<FichaMedica> buscarTodos();

    FichaMedica buscarPorId(Long id);

    void guardar(FichaMedica fichaMedica);

    void borrarPorId(Long id);

}
