package com.example.energias.renovables.controller;

import com.example.energias.renovables.DTO.UsuarioDTO;
import com.example.energias.renovables.DTO.UsuarioRequestDTO;

import com.example.energias.renovables.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @PostMapping
    public UsuarioDTO guardar(@RequestBody UsuarioRequestDTO usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioActualizado) {
        Optional<UsuarioDTO> usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarParcialmente(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        Optional<UsuarioDTO> usuario = usuarioService.actualizarParcialmente(id, cambios);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
