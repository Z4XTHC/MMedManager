package com.mangosoft.MMedManager.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangosoft.MMedManager.model.entities.Medico;
import com.mangosoft.MMedManager.model.entities.Usuario;

@Repository
public interface iUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    @Query("SELECT m FROM Medico m JOIN FETCH m.usuario")
    List<Medico> findAllWithUsuarios();

    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.nombre = 'Recepcionista'")
    List<Usuario> findAllRecepcionistas();

    @Query("SELECT u FROM Usuario u WHERE u.medico IS NOT NULL")
    List<Usuario> findAllMedicos();

    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.nombre = :nombreRol")
    List<Usuario> findAllByRol(@Param("nombreRol") String nombreRol);

    Optional<Usuario> findByUsername(String username);

}