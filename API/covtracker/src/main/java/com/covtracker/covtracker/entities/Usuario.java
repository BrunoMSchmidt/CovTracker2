package com.covtracker.covtracker.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @Column(name = "cpfusu", nullable = false, precision = 11)
    private BigDecimal cpf;

    @Column(name = "nomusu", nullable = false, length = 60)
    private String nome;

    @Column(name = "emausu", unique = true, length = 50)
    private String email;

    @Column(name = "senusu", nullable = false, length = 60)
    private String senha;

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
    private Empresa empresa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codtipusu", nullable = false)
    private TipoUsuario tipoUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codend", nullable = false)
    private Endereco endereco;

    @OneToMany(orphanRemoval = true)
    private List<UsuarioTelefone> usuarioTelefones;

}