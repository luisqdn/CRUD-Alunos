package com.example.CadastrarAlunos.dto;

import java.time.LocalDate;

public record MatriculaDTO(String numeroMatricula, String nomeCurso, LocalDate dataInicio) {
}
