package com.mangosoft.MMedManager.model.services;

import java.util.List;

import com.mangosoft.MMedManager.model.entities.ArchivoFichaMedica;

public interface iArchivoFichaMedicaService {

    List<ArchivoFichaMedica> buscarTodos();

    ArchivoFichaMedica buscarPorId(Long id);

    void guardar(ArchivoFichaMedica archivoFichaMedica);

    void borrarPorId(Long id);

}
