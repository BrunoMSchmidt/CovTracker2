package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "tipo_usuario", indexes = {
        @Index(name = "tipo_usuario_nomtipusu_key", columnList = "nomtipusu", unique = true)
})
@Entity
@Getter
@Setter
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codtipusu", nullable = false)
    private Integer id;

    @Column(name = "nomtipusu", nullable = false, length = 30)
    private String nome;

}