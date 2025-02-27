package com.example.energias.renovables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.energias.renovables.DTO.LoginRequestDTO;
import com.example.energias.renovables.DTO.UsuarioDTO;
import com.example.energias.renovables.DTO.UsuarioRequestDTO;
import com.example.energias.renovables.service.AuthService;
import com.example.energias.renovables.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioRequestDTO usuarioDTO) {
        UsuarioDTO usuario = usuarioService.findByEmail(usuarioDTO.getEmail());

        if (usuario != null) {
            return ResponseEntity.badRequest().body("El email ya está en uso");
        }

        usuarioService.guardar(usuarioDTO);

        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            AuthResponse response = authService.login(request.getEmail(), request.getPassword());

            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
