package com.mangosoft.MMedManager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mangosoft.MMedManager.model.entities.Paciente;

@Repository
public interface iRepositoryPaciente extends JpaRepository<Paciente, Long> {

}
