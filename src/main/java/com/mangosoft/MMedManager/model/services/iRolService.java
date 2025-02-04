package com.mangosoft.MMedManager.model.services;

import java.util.List;
import java.util.Optional;

import com.mangosoft.MMedManager.model.entities.Rol;

public interface iRolService {

    List<Rol> buscarTodos();

    public Optional<Rol> buscarPorId(Long id);

    void guardar(Rol rol);

    void borrarPorId(Long id);

}
