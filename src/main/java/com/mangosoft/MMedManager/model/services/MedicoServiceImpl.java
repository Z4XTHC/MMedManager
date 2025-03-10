package com.mangosoft.MMedManager.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Medico;
import com.mangosoft.MMedManager.model.repository.iMedicoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MedicoServiceImpl implements iMedicoService {

    @Autowired
    private iMedicoRepository medicoRepo;

    @Override
    public List<Medico> buscarTodos() {
        return medicoRepo.findAll();
    }

    @Override
    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepo.findById(id);
    }

    @Override
    public void guardar(Medico medico) {
        if (medico.getId() != null) {
            Optional<Medico> medicoExistente = medicoRepo.findById(medico.getId());
            if (medicoExistente.isPresent()) {
                medicoRepo.save(medico);
            } else {
                throw new EntityNotFoundException("El medico no existe");
            }
        } else {
            medicoRepo.save(medico);
        }
    }

    @Override
    public void borrarPorId(Long id) {
        medicoRepo.deleteById(id);
    }
}
