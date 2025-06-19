package com.sistemaescolar.MODELS;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Disciplina disciplina;

    private Double notaP1;
    private Double notaP2;
    private Double notaT5;

    public Double getMedia() {
        if (notaP1 == null || notaP2 == null || notaT5 == null) return null;
        return (notaP1 + notaP2 + notaT5) / 3.0;
    }
}
