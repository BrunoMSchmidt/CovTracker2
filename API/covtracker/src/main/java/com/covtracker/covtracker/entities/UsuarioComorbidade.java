package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "usuario_comorbidade")
@Entity
@Getter
@Setter
public class UsuarioComorbidade {
    @EmbeddedId
    private UsuarioComorbidadeId id;
}