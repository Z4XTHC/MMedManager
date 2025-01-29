package com.mangosoft.MMedManager.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Rol;
import com.mangosoft.MMedManager.model.repository.iRolRepository;

@Service
public class RolServiceImpl implements iRolService {

    @Autowired
    private iRolRepository rolRepo;

    @Override
    public List<Rol> buscarTodos() {
        return rolRepo.findAll();
    }

    @Override
    public Rol buscarPorId(Long id) {
        return rolRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Rol rol) {
        rolRepo.save(rol);
    }

    @Override
    public void borrarPorId(Long id) {
        rolRepo.deleteById(id);
    }
}
