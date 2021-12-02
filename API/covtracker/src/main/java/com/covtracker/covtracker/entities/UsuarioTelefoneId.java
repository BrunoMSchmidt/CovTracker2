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
public class UsuarioTelefoneId implements Serializable {
    private static final long serialVersionUID = 6726915268952800012L;
    @Column(name = "cpfusu", nullable = false, precision = 11)
    private BigDecimal cpf;
    @Column(name = "telpac", nullable = false, precision = 11)
    private BigDecimal telefone;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsuarioTelefoneId entity = (UsuarioTelefoneId) o;
        return Objects.equals(this.telefone, entity.telefone) &&
                Objects.equals(this.telefone, entity.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefone, cpf);
    }
}