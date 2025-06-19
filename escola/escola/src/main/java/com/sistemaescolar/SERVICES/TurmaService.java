package com.sistemaescolar.SERVICES;

import com.sistemaescolar.MODELS.Turma;
import com.sistemaescolar.REPOSITORIES.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }


    public Optional<Turma> buscarPorId(Long id) {
        return turmaRepository.findById(id);
    }

    public void salvar(Turma turma) {
        turmaRepository.save(turma);
    }

    public void deletar(Long id) {
        turmaRepository.deleteById(id);
    }
}
