package com.covtracker.covtracker.dto;


import com.covtracker.covtracker.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TesteDTO {
    private Integer id;
    private BigDecimal cpfUsuario;
    private String resultado;
    private LocalDate dataTeste;
}
