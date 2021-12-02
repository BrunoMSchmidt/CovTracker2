package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "usuario_telefone")
@Entity
@Getter
@Setter
public class UsuarioTelefone {
    @EmbeddedId
    private UsuarioTelefoneId id;
}