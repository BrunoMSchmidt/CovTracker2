package com.covtracker.covtracker.dto;

import com.covtracker.covtracker.entities.Usuario;
import com.covtracker.covtracker.entities.Vacina;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UsuarioVacinaDTO {

    private Integer id;
    private LocalDate datusuvac;
    private Integer dosusuvac;
    private Vacina vacina;

}
