package com.mangosoft.MMedManager.model.repository;

public interface iRepositoryUsuario extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}