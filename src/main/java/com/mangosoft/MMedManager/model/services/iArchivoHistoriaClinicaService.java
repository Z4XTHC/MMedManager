package com.mangosoft.MMedManager.model.services;

import java.util.List;
import com.mangosoft.MMedManager.model.entities.ArchivoHistoriaClinica;

public interface iArchivoHistoriaClinicaService {

    List<ArchivoHistoriaClinica> buscarTodos();

    ArchivoHistoriaClinica buscarPorId(Long id);

    void guardar(ArchivoHistoriaClinica archivoHistoriaClinica);

    void borrarPorId(Long id);

}
