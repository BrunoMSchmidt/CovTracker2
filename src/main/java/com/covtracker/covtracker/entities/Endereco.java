package com.covtracker.covtracker.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "endereco")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "cepend", nullable = false, precision = 8)
    private BigDecimal cep;

    @Column(name = "baiend", length = 40)
    private String bairro;

}