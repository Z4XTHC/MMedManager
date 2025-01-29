package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Medico;
import com.mangosoft.MMedManager.model.repository.iMedicoRepository;

@Service
public class MedicoServiceImpl implements iMedicoService {

    @Autowired
    private iMedicoRepository medicoRepo;

    @Override
    public List<Medico> buscarTodos() {
        return medicoRepo.findAll();
    }

    @Override
    public Medico buscarPorId(Long id) {
        return medicoRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Medico medico) {
        medicoRepo.save(medico);
    }

    @Override
    public void borrarPorId(Long id) {
        medicoRepo.deleteById(id);
    }
}
