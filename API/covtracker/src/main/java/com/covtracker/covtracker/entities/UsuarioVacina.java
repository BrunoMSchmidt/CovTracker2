package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "usuario_vacina")
@Entity
@Getter
@Setter
public class UsuarioVacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codusuvac", nullable = false)
    private Integer id;

    @Column(name = "datusuvac", nullable = false)
    private LocalDate data;

    @Column(name = "dosusuvac", nullable = false)
    private Integer dose;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codvac", nullable = false)
    private Vacina vacina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cpfusu", nullable = false)
    private Usuario usuario;

}