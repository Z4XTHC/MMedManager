package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.Examen;

public interface iExamenService {

    List<Examen> buscarTodos();

    Examen buscarPorId(Long id);

    void guardar(Examen examen);

    void borrarPorId(Long id);

}
