package com.covtracker.covtracker.dto;

import com.covtracker.covtracker.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private Usuario usuario;
    private String token;

}
