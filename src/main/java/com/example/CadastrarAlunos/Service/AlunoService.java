package com.example.CadastrarAlunos.Service;
import com.example.CadastrarAlunos.dto.AlunoRequest;
import com.example.CadastrarAlunos.dto.AlunoResponse;
import com.example.CadastrarAlunos.dto.MatriculaDTO;
import com.example.CadastrarAlunos.mapper.AlunoMapper;
import com.example.CadastrarAlunos.model.Aluno;
import com.example.CadastrarAlunos.model.Matricula;
import com.example.CadastrarAlunos.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    public AlunoResponse salvar(AlunoRequest request){
        Aluno aluno = alunoMapper.toEntity(request);
        alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoResponse> listarTodos(){
        return alunoRepository.findAll().stream().map(alunoMapper::toResponse).toList();
    }

    public List<MatriculaDTO> listarMatriculas(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
        return  aluno.getMatriculas().stream().map( m -> new MatriculaDTO(m.getNumeroMatricula(), m.getNomeCurso(), m.getDataInicio())).toList();
    }

    public void removerAluno(Long id){
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado.");
        }
        alunoRepository.deleteById(id);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest request) {
        Aluno a = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
        a.setNome(request.nome());
        a.setTelefone(request.telefone());
        a.setDataNascimento(request.dataNascimento());

        for (MatriculaDTO m : request.matriculas()) {
            Matricula matricula = new Matricula();
            matricula.setNumeroMatricula(m.numeroMatricula());
            matricula.setNomeCurso(m.nomeCurso());
            matricula.setDataInicio(m.dataInicio());
            a.getMatriculas().add(matricula);
        }
            return alunoMapper.toResponse(alunoRepository.save(a));
    }
}
