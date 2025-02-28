package com.example.energias.renovables.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.example.energias.renovables.DTO.UsuarioDTO;
import com.example.energias.renovables.controller.AuthResponse;

@Service
public class AuthService {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public AuthService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public AuthResponse login(String email, String password) {
        UsuarioDTO usuario = usuarioService.findByEmail(email);

        if (usuario == null || !passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);

        String token = Jwts.builder()
            .setSubject(Long.toString(usuario.getId()))
            .claim("email", usuario.getEmail())
            .claim("name", usuario.getNombre())
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes(StandardCharsets.UTF_8))
            .compact();

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getCreatedAt());
        return new AuthResponse(token, usuarioDTO);
    }
}
