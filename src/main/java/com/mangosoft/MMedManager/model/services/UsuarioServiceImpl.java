package com.mangosoft.MMedManager.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangosoft.MMedManager.model.entities.Usuario;
import com.mangosoft.MMedManager.model.repository.iUsuarioRepository;

@Service
public class UsuarioServiceImpl implements iUsuarioService {

    @Autowired
    private iUsuarioRepository usuarioRepo;

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepo.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public void guardar(Usuario usuario) {
        if (usuario.getId() != null) {
            Optional<Usuario> usuarioExistente = usuarioRepo.findById(usuario.getId());
            if (usuarioExistente.isPresent()) {
                usuarioRepo.save(usuario); // ACTUALIZACIÓN.
            } else {
                throw new RuntimeException("El usuario no existe.");
            }
        } else {
            usuarioRepo.save(usuario); // NUEVO USUARIO.
        }
    }

    @Override
    public void borrarPorId(Long id) {
        usuarioRepo.deleteById(id);
    }

    // @Override
    // public void eliminarOrHabilitar(Long id, boolean estado) {
    // // cambiar unicamente el activo por falso.
    // Usuario usuario = buscarPorId(id);

    // if (estado == false) {
    // usuario.setActivo(true); // SE HABILITA SI ES FALSO.
    // } else {
    // usuario.setActivo(false); // SE DESHABILITA SI ES VERDADERO.
    // }
    // guardar(usuario);
    // }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepo.findByUsername(username);
    }

}
