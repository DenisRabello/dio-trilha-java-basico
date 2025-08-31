package com.exemplo.gestaoescolar.repository;

import com.exemplo.gestaoescolar.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {}
