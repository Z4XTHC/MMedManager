package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.ArchivoFichaMedica;
import com.mangosoft.MMedManager.model.repository.iArchivoFichaMedicaRepository;

@Service
public class ArchivoFichaMedicaServiceImpl implements iArchivoFichaMedicaService {

    @Autowired
    private iArchivoFichaMedicaRepository archivoRepo;

    @Override
    public List<ArchivoFichaMedica> buscarTodos() {
        return archivoRepo.findAll();
    }

    @Override
    public ArchivoFichaMedica buscarPorId(Long id) {
        return archivoRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(ArchivoFichaMedica archivo) {
        archivoRepo.save(archivo);
    }

    @Override
    public void borrarPorId(Long id) {
        archivoRepo.deleteById(id);
    }
}
