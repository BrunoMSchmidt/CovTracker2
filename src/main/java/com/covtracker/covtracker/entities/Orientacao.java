package com.covtracker.covtracker.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "orientacao")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orientacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codori", nullable = false)
    private Integer codigo;

    @Column(name = "desori", nullable = false)
    private String descricao;

    public Orientacao(String descricao){
        this.descricao = descricao;
    }
}