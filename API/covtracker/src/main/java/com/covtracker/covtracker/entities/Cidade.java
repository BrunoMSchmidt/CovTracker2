package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "cidade")
@Entity
@Getter
@Setter
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codcid", nullable = false)
    private Integer id;

    @Column(name = "nomcid", nullable = false, length = 40)
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sigest", nullable = false)
    private Estado siglaEstado;

}