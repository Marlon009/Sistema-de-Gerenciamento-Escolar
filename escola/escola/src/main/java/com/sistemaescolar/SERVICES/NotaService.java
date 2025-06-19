package com.sistemaescolar.SERVICES;

import com.sistemaescolar.MODELS.Nota;
import com.sistemaescolar.REPOSITORIES.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public List<Nota> listarTodas() {
        return notaRepository.findAll();
    }

    public void salvar(Nota nota) {
        notaRepository.save(nota);
    }

    public List<Nota> buscarPorAluno(Long alunoId) {
        return notaRepository.findByAlunoId(alunoId);
    }

    public List<Nota> buscarPorDisciplina(Long disciplinaId) {
        return notaRepository.findByDisciplinaId(disciplinaId);
    }
}
