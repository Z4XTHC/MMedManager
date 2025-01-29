package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.Receta;

public interface iRecetaService {

    List<Receta> buscarTodos();

    Receta buscarPorId(Long id);

    void guardar(Receta receta);

    void borrarPorId(Long id);

}
