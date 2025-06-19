package com.sistemaescolar.REPOSITORIES;

import com.sistemaescolar.MODELS.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByAlunoId(Long alunoId);
    List<Nota> findByDisciplinaId(Long disciplinaId);
}
