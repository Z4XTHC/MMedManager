package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.Cita;

public interface iCitaService {

    List<Cita> buscarTodos();

    Cita buscarPorId(Long id);

    void guardar(Cita cita);

    void borrarPorId(Long id);

}
