package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "vacina", indexes = {
        @Index(name = "vacina_nomvac_key", columnList = "nomvac", unique = true)
})
@Entity
@Getter
@Setter
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codvac", nullable = false)
    private Integer id;

    @Column(name = "nomvac", nullable = false, length = 30)
    private String nome;

    @Column(name = "quadosvac", nullable = false)
    private Integer quantidadeDoses;

}