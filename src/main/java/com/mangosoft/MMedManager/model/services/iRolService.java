package com.mangosoft.MMedManager.model.services;

import java.util.List;

import com.mangosoft.MMedManager.model.entities.Rol;

public interface iRolService {

    List<Rol> buscarTodos();

    Rol buscarPorId(Long id);

    void guardar(Rol rol);

    void borrarPorId(Long id);

}
