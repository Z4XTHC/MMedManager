package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Cita;
import com.mangosoft.MMedManager.model.repository.iCitaRepository;

@Service
public class CitaServiceImpl implements iCitaService {

    @Autowired
    private iCitaRepository citaRepo;

    @Override
    public List<Cita> buscarTodos() {
        return citaRepo.findAll();
    }

    @Override
    public Cita buscarPorId(Long id) {
        return citaRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Cita cita) {
        citaRepo.save(cita);
    }

    @Override
    public void borrarPorId(Long id) {
        citaRepo.deleteById(id);
    }
}
