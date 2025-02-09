package com.example.energias.renovables.service;

import com.example.energias.renovables.DTO.UsuarioDTO;
import com.example.energias.renovables.DTO.UsuarioRequestDTO;
import com.example.energias.renovables.model.Usuario;
import com.example.energias.renovables.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios y convertirlos en UsuarioDTO
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getCreatedAt()))
                .collect(Collectors.toList());
    }

    // Obtener usuario por ID
    public Optional<UsuarioDTO> obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getCreatedAt()));
    }

    // Guardar usuario desde un UsuarioRequestDTO
    public UsuarioDTO guardar(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRequestDTO.getNombre());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setPassword(usuarioRequestDTO.getPassword()); // ⚠️ Debería encriptarse antes de guardarse
        usuario.setCreatedAt(java.time.LocalDateTime.now());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioGuardado.getId(), usuarioGuardado.getNombre(), usuarioGuardado.getEmail(), usuarioGuardado.getCreatedAt());
    }

    // Actualizar usuario completamente
    public Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioRequestDTO usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setPassword(usuarioActualizado.getPassword());
            Usuario usuarioGuardado = usuarioRepository.save(usuario);
            return new UsuarioDTO(usuarioGuardado.getId(), usuarioGuardado.getNombre(), usuarioGuardado.getEmail(), usuarioGuardado.getCreatedAt());
        });
    }

    // Actualización parcial (PATCH)
    public Optional<UsuarioDTO> actualizarParcialmente(Long id, Map<String, Object> cambios) {
        return usuarioRepository.findById(id).map(usuario -> {
            if (cambios.containsKey("nombre")) {
                usuario.setNombre((String) cambios.get("nombre"));
            }
            if (cambios.containsKey("email")) {
                usuario.setEmail((String) cambios.get("email"));
            }
            if (cambios.containsKey("password")) {
                usuario.setPassword((String) cambios.get("password"));
            }
            Usuario usuarioGuardado = usuarioRepository.save(usuario);
            return new UsuarioDTO(usuarioGuardado.getId(), usuarioGuardado.getNombre(), usuarioGuardado.getEmail(), usuarioGuardado.getCreatedAt());
        });
    }

    // Eliminar usuario
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
