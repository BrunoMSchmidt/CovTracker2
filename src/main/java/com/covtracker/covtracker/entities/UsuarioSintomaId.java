package com.covtracker.covtracker.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class UsuarioSintomaId implements Serializable {
    private static final long serialVersionUID = 479148770657572982L;
    @Column(name = "cpfusu", nullable = false, precision = 11)
    private BigDecimal cpfusu;
    @Column(name = "codsin", nullable = false)
    private Integer codsin;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioSintomaId entity = (UsuarioSintomaId) o;
        return Objects.equals(this.cpfusu, entity.cpfusu) &&
                Objects.equals(this.codsin, entity.codsin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfusu, codsin);
    }
}