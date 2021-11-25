package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.dto.AuthRequest;
import com.covtracker.covtracker.dto.AuthResponse;
import com.covtracker.covtracker.repositories.UsuarioRepository;
import com.covtracker.covtracker.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class AutenticacaoController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(path = "/autenticar/teste")
    public String testeAutenticacao(){
        return "autenticado";
    }

    @PostMapping("/autenticar")
    public AuthResponse generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getSenha()
                    )
            );
        } catch(Exception e) {
            throw new Exception("Invalid username/password");
        }

        return new AuthResponse(usuarioRepository.findByEmail(authRequest.getEmail()), jwtUtil.generateToken(authRequest.getEmail()));
    }

}
