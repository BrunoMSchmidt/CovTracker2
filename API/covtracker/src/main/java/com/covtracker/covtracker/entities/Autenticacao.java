package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "autenticacao")
@Entity
@Getter
@Setter
public class Autenticacao {
    @Id
    @Column(name = "cpfusu", nullable = false, precision = 11)
    private BigDecimal id;

    @Column(name = "senaut", nullable = false)
    private String senha;

}