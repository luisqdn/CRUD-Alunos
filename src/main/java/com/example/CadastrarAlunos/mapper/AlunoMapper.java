package com.example.CadastrarAlunos.mapper;

import com.example.CadastrarAlunos.dto.AlunoRequest;
import com.example.CadastrarAlunos.dto.AlunoResponse;
import com.example.CadastrarAlunos.dto.MatriculaDTO;
import com.example.CadastrarAlunos.model.Aluno;
import com.example.CadastrarAlunos.model.Matricula;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.nome());
        aluno.setTelefone(request.telefone());
        aluno.setDataNascimento(request.dataNascimento());

        List<Matricula> matriculas = request.matriculas().stream().map(m ->{
            Matricula matricula = new Matricula();
            matricula.setNumeroMatricula(m.numeroMatricula());
            matricula.setNomeCurso(m.nomeCurso());
            matricula.setDataInicio(m.dataInicio());
            matricula.setAluno(aluno);
            return matricula;
        }).toList();
        aluno.setMatriculas(matriculas);
        return aluno;
    }
    public AlunoResponse toResponse(Aluno aluno){
        List< MatriculaDTO> matriculaDTOS = aluno.getMatriculas().stream().map(m ->
            new MatriculaDTO(m.getNumeroMatricula(), m.getNomeCurso(), m.getDataInicio())).toList();
        return  new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getTelefone(), aluno.getDataNascimento(), matriculaDTOS);
    }
}
