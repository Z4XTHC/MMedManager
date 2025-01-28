package com.mangosoft.MMedManager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mangosoft.MMedManager.model.entities.Medico;

@Repository
public interface iRepositoryMedico extends JpaRepository<Medico, Long> {

}
