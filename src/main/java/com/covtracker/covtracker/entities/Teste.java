package com.covtracker.covtracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "teste")
@Entity
@Getter
@Setter
public class Teste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codtes", nullable = false)
    private Integer id;

    @Column(name = "restes", nullable = false, length = 1)
    private String resultado;

    @Column(name = "dattes", nullable = false)
    private LocalDate dataTeste;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cpfusu", nullable = false)
    private Usuario usuario;
}