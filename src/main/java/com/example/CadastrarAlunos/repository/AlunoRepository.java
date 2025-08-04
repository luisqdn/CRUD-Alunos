package com.example.CadastrarAlunos.repository;

import com.example.CadastrarAlunos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
