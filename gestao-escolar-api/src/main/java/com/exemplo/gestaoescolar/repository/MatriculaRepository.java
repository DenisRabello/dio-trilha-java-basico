package com.exemplo.gestaoescolar.repository;

import com.exemplo.gestaoescolar.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {}
