package com.mangosoft.MMedManager.model.services;

import java.util.List;

import com.mangosoft.MMedManager.model.entities.Usuario;

public interface iUsuarioService {

    public List<Usuario> buscarTodos();

    public Usuario buscarPorId(Long id);

    public void guardar(Usuario usuario);

    public void borrarPorId(Long id);

    public void eliminarOrHabilitar(Long id, boolean estado);

}
