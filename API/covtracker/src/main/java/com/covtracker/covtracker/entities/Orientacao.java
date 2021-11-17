package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "orientacao")
@Entity
@Getter
@Setter
public class Orientacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codori", nullable = false)
    private Integer id;

    @Column(name = "desori", nullable = false)
    private String descricao;

}