package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "bairro")
@Entity
@Getter
@Setter
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codbai", nullable = false)
    private Integer id;

    @Column(name = "nombai", nullable = false, length = 40)
    private String nome;

    @Column(name = "cepbai", nullable = false, precision = 8)
    private BigDecimal cep;

}