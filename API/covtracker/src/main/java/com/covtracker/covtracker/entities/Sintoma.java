package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "sintoma", indexes = {
        @Index(name = "sintoma_nomsin_key", columnList = "nomsin", unique = true)
})
@Entity
@Getter
@Setter
public class Sintoma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codsin", nullable = false)
    private Integer id;

    @Column(name = "nomsin", nullable = false, length = 30)
    private String nome;

    @Column(name = "crisin", nullable = false)
    private Integer criticidade;

}