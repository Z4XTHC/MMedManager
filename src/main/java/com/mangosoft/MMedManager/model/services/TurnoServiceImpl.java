package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Turno;
import com.mangosoft.MMedManager.model.repository.iTurnoRepository;

@Service
public class TurnoServiceImpl implements iTurnoService {

    @Autowired
    private iTurnoRepository turnoRepo;

    @Override
    public List<Turno> buscarTodos() {
        return turnoRepo.findAll();
    }

    @Override
    public Turno buscarPorId(Long id) {
        return turnoRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Turno turno) {
        turnoRepo.save(turno);
    }

    @Override
    public void borrarPorId(Long id) {
        turnoRepo.deleteById(id);
    }
}
