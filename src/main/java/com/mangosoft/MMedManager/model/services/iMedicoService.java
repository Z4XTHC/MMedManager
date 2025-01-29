package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.Medico;

public interface iMedicoService {

    List<Medico> buscarTodos();

    Medico buscarPorId(Long id);

    void guardar(Medico medico);

    void borrarPorId(Long id);

}
