package com.mangosoft.MMedManager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mangosoft.MMedManager.model.entities.Rol;

public interface iRepositoryRol extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}