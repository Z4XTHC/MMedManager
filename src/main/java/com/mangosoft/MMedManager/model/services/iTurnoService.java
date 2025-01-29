package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.Turno;

public interface iTurnoService {

    List<Turno> buscarTodos();

    Turno buscarPorId(Long id);

    void guardar(Turno turno);

    void borrarPorId(Long id);

}
