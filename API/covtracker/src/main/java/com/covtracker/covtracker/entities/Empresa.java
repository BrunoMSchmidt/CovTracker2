 package com.covtracker.covtracker.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "empresa", indexes = {
        @Index(name = "empresa_razsocemp_key", columnList = "razsocemp", unique = true)
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    @Id
    @Column(name = "cnpjemp", nullable = false, precision = 14)
    private BigDecimal cnpj;

    @Column(name = "nomfanemp", length = 60)
    private String nomeFantasia;

    @Column(name = "razsocemp", nullable = false, length = 60)
    private String razaoSocial;

    @Column(name = "telemp", nullable = false, precision = 11)
    private BigDecimal telefone;

    @Column(name = "emaemp", nullable = false, length = 50)
    private String email;

    @Column(name = "areatuemp", length = 50)
    private String areaDeAtuacao;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "codend", nullable = false)
    private Endereco endereco;

}