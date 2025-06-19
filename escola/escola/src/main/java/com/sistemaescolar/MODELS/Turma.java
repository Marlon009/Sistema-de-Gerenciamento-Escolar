package com.sistemaescolar.MODELS;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String identificador;

    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos = new ArrayList<>();

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        aluno.setTurma(this);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
        aluno.setTurma(null);
    }

    public Aluno buscarAluno(String matricula) {
        return alunos.stream()
                .filter(a -> a.getMatricula().equals(matricula))
                .findFirst().orElse(null);
    }

    public double calcularMediaTurma() {
        if (alunos.isEmpty()) return 0.0;
        return alunos.stream().mapToDouble(Aluno::calcularMedia).average().orElse(0.0);
    }
}
