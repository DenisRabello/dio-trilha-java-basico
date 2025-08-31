package com.exemplo.gestaoescolar.repository;

import com.exemplo.gestaoescolar.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {}
