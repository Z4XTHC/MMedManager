package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.HistoriaClinica;
import com.mangosoft.MMedManager.model.repository.iHistoriaClinicaRepository;

@Service
public class HistoriaClinicaServiceImpl implements iHistoriaClinicaService {

    @Autowired
    private iHistoriaClinicaRepository historiaRepo;

    @Override
    public List<HistoriaClinica> buscarTodos() {
        return historiaRepo.findAll();
    }

    @Override
    public HistoriaClinica buscarPorId(Long id) {
        return historiaRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(HistoriaClinica historiaClinica) {
        historiaRepo.save(historiaClinica);
    }

    @Override
    public void borrarPorId(Long id) {
        historiaRepo.deleteById(id);
    }
}
