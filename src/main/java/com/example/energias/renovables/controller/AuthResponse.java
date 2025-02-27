package com.example.energias.renovables.controller;

import com.example.energias.renovables.DTO.UsuarioDTO;

public class AuthResponse {
    private String accessToken;
    private UsuarioDTO usuario;

    public AuthResponse(String accessToken, UsuarioDTO usuario) {
        this.accessToken = accessToken;
        this.usuario = usuario;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}
