package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.FichaMedica;
import com.mangosoft.MMedManager.model.repository.iFichaMedicaRepository;

@Service
public class FichaMedicaServiceImpl implements iFichaMedicaService {

    @Autowired
    private iFichaMedicaRepository fichaRepo;

    @Override
    public List<FichaMedica> buscarTodos() {
        return fichaRepo.findAll();
    }

    @Override
    public FichaMedica buscarPorId(Long id) {
        return fichaRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(FichaMedica fichaMedica) {
        fichaRepo.save(fichaMedica);
    }

    @Override
    public void borrarPorId(Long id) {
        fichaRepo.deleteById(id);
    }
}
