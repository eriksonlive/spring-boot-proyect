package com.example.energias.renovables.repository;

import com.example.energias.renovables.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional <Usuario> findByEmail(String email);
}
