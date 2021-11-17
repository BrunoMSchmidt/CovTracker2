package com.covtracker.covtracker.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @Column(name = "cpfusu", nullable = false, precision = 11)
    private BigInteger cpf;

    @Column(name = "nomusu", nullable = false, length = 60)
    private String nome;

    @Column(name = "datnasusu")
    private LocalDate dataNascimento;

    @Column(name = "sexusu", length = 1)
    private String sexo;

    @Column(name = "pesusu", nullable = false, precision = 3)
    private BigDecimal peso;

    @Column(name = "altusu", nullable = false, precision = 3, scale = 2)
    private BigDecimal altura;

    @Column(name = "funempusu", nullable = false, length = 40)
    private String funcaoEmpresa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cnpjemp", nullable = false)
    private Empresa cnpjEmpresa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codtipusu", nullable = false)
    private TipoUsuario codigoTipoUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codend", nullable = false)
    private Endereco codigoEndereco;

}