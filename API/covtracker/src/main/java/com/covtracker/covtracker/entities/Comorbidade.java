package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "comorbidade", indexes = {
        @Index(name = "comorbidade_nomcom_key", columnList = "nomcom", unique = true)
})
@Entity
@Getter
@Setter
public class Comorbidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codcom", nullable = false)
    private Integer id;

    @Column(name = "nomcom", nullable = false, length = 100)
    private String nomcom;

}