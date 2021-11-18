package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "usuario_sintoma")
@Entity
@Getter
@Setter
public class UsuarioSintoma {
    @EmbeddedId
    private UsuarioSintomaId id;

    @Column(name = "intsin", nullable = false)
    private Integer intensidade;

    @Column(name = "datinisin", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "datfinsin")
    private LocalDate dataFim;

}