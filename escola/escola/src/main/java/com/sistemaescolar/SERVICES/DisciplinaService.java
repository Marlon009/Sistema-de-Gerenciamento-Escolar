package com.sistemaescolar.SERVICES;

import com.sistemaescolar.MODELS.Disciplina;
import com.sistemaescolar.REPOSITORIES.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public void deletar(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
