package com.mangosoft.MMedManager.model.services;

import java.util.List;
import java.util.Optional;

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
    public Optional<AreaMedica> buscarPorId(Long id) {
        return areaRepo.findById(id);
    }

    @Override
    public void guardar(AreaMedica areaMedica) {
        if (areaMedica.getId() != null) {
            Optional<AreaMedica> areaMedicaExistente = areaRepo.findById(areaMedica.getId());
            if (areaMedicaExistente.isPresent()) { // Actualización
                areaRepo.save(areaMedica);
            } else {
                throw new RuntimeException("El área médica no existe.");
            }
        } else {
            areaRepo.save(areaMedica); // Creación del nuevo área médica
        }
    }

    @Override
    public void borrarPorId(Long id) {
        areaRepo.deleteById(id);
    }
}
