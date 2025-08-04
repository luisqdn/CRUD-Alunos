package com.example.CadastrarAlunos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroMatricula;
    private String nomeCurso;

    @CreatedDate
    @Column(name = "data_inclusao", updatable = false)
    private LocalDate dataInicio;

    @ManyToOne
    @JoinColumn(name= "aluno id")
    private  Aluno aluno;
}
