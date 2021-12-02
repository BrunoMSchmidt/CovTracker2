package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "autenticacao")
@Entity
@Getter
@Setter
public class Autenticacao {
    @Id
    @Column(name = "cpfusu", nullable = false, precision = 11)
    private BigDecimal cpf;

    @Column(name = "senaut", nullable = false)
    private String senha;

}