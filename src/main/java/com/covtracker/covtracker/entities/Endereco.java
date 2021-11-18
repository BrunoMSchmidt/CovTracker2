package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "endereco")
@Entity
@Getter
@Setter
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codend", nullable = false)
    private Integer id;

    @Column(name = "ruaend", nullable = false, length = 40)
    private String rua;

    @Column(name = "numend", length = 8)
    private String numero;

    @Column(name = "comend", length = 40)
    private String complemento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codcid", nullable = false)
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "codbai")
    private Bairro bairro;

}