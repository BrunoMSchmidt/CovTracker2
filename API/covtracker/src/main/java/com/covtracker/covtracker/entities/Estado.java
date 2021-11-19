package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "estado")
@Entity
@Getter
@Setter
public class Estado {
    @Id
    @Column(name = "sigest", nullable = false, length = 2)
    private String sigla;

    @Column(name = "nomest", nullable = false, length = 40)
    private String nome;

}