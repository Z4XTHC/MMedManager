package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Examen;
import com.mangosoft.MMedManager.model.repository.iExamenRepository;

@Service
public class ExamenServiceImpl implements iExamenService {

    @Autowired
    private iExamenRepository examenRepo;

    @Override
    public List<Examen> buscarTodos() {
        return examenRepo.findAll();
    }

    @Override
    public Examen buscarPorId(Long id) {
        return examenRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Examen examen) {
        examenRepo.save(examen);
    }

    @Override
    public void borrarPorId(Long id) {
        examenRepo.deleteById(id);
    }
}
