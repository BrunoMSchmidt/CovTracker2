package com.covtracker.covtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSintomaDTO {
    private Integer idSintoma;
    private BigDecimal cpfUsuario;
    private Integer intensidade;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
