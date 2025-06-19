package com.sistemaescolar.MODELS;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double nota = 0.0;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> alunos = new ArrayList<>();

    public String exibirInformacoes() {
        return "Disciplina: " + nome;
    }
}
