package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.HistoriaClinica;

public interface iHistoriaClinicaService {

    List<HistoriaClinica> buscarTodos();

    HistoriaClinica buscarPorId(Long id);

    void guardar(HistoriaClinica historiaClinica);

    void borrarPorId(Long id);

}
