package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class UsuarioComorbidadeId implements Serializable {
    private static final long serialVersionUID = 9191599456258884676L;
    @Column(name = "cpfusu", nullable = false, precision = 11)
    private BigDecimal cpfusu;
    @Column(name = "codcom", nullable = false)
    private Integer codcom;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioComorbidadeId entity = (UsuarioComorbidadeId) o;
        return Objects.equals(this.codcom, entity.codcom) &&
                Objects.equals(this.cpfusu, entity.cpfusu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codcom, cpfusu);
    }
}