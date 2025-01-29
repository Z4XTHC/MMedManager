package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.AreaMedica;
import com.mangosoft.MMedManager.model.repository.iAreaMedicaRepository;

@Service
public class AreaMedicaServiceImpl implements iAreaMedicaService {

    @Autowired
    private iAreaMedicaRepository areaRepo;

    @Override
    public List<AreaMedica> buscarTodos() {
        return areaRepo.findAll();
    }

    @Override
    public AreaMedica buscarPorId(Long id) {
        return areaRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(AreaMedica areaMedica) {
        areaRepo.save(areaMedica);
    }

    @Override
    public void borrarPorId(Long id) {
        areaRepo.deleteById(id);
    }
}
