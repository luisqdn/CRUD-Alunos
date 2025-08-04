package com.example.CadastrarAlunos.repository;

import com.example.CadastrarAlunos.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository  extends JpaRepository<Matricula, Long> {
}
