package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.ArchivoHistoriaClinica;
import com.mangosoft.MMedManager.model.repository.iArchivoHistoriaClinicaRepository;

@Service
public class ArchivoHistoriaClinicaServiceImpl implements iArchivoHistoriaClinicaService {

    @Autowired
    private iArchivoHistoriaClinicaRepository archivoRepo;

    @Override
    public List<ArchivoHistoriaClinica> buscarTodos() {
        return archivoRepo.findAll();
    }

    @Override
    public ArchivoHistoriaClinica buscarPorId(Long id) {
        return archivoRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(ArchivoHistoriaClinica archivo) {
        archivoRepo.save(archivo);
    }

    @Override
    public void borrarPorId(Long id) {
        archivoRepo.deleteById(id);
    }
}
