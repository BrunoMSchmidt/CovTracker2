package com.covtracker.covtracker.dto;

import com.covtracker.covtracker.entities.Usuario;
import com.covtracker.covtracker.entities.Vacina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioVacinaDTO {

    private Integer idVacina;
    private BigDecimal cpfUsuario;
    private LocalDate datusuvac;
    private Integer dosusuvac;

}
