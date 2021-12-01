package com.covtracker.covtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioComorbidadeDTO {
    private BigDecimal cpfUsuario;
    private Integer idComorbidade;
}
