package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "vacinacao")
@Entity
@Getter
@Setter
public class Vacinacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codvaccao", nullable = false)
    private Integer id;

    @Column(name = "datvaccao", nullable = false)
    private LocalDate data;

    @Column(name = "dosvaccao", nullable = false)
    private Integer dose;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codvac", nullable = false)
    private Vacina codigoVacina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cpfusu", nullable = false)
    private Usuario cpfusu;

}