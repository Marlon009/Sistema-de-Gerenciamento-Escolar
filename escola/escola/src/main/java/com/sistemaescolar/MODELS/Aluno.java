package com.sistemaescolar.MODELS;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    private String nome;
    private int idade;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @Transient
    private List<Nota> notas; // para armazenar temporariamente as notas

    @ManyToMany
    @JoinTable(
            name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas = new ArrayList<>();

    public double calcularMedia() {
        if (notas == null || notas.isEmpty()) return 0.0;

        double soma = 0.0;
        int count = 0;

        for (Nota nota : notas) {
            Double mediaNota = nota.getMedia();
            if (mediaNota != null) {
                soma += mediaNota;
                count++;
            }
        }

        return count == 0 ? 0.0 : Math.round((soma / count) * 100.0) / 100.0;
    }
}
