package com.mangosoft.MMedManager.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Rol;
import com.mangosoft.MMedManager.model.repository.iRolRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RolServiceImpl implements iRolService {

    @Autowired
    private iRolRepository rolRepo;

    @Override
    public List<Rol> buscarTodos() {
        return rolRepo.findAll();
    }

    @Override
    public Optional<Rol> buscarPorId(Long id) {
        return rolRepo.findById(id);
    }

    @Override
    public void guardar(Rol rol) {
        if (rol.getId() != null) {
            Optional<Rol> rolExistente = rolRepo.findById(rol.getId());
            if (rolExistente.isPresent()) { // Actualización.
                rolRepo.save(rol);
            } else {
                throw new EntityNotFoundException("El rol no existe.");
            }
        } else {
            rolRepo.save(rol); // Creación del nuevo rol.
        }
    }

    @Override
    public void borrarPorId(Long id) {
        rolRepo.deleteById(id);
    }
}
