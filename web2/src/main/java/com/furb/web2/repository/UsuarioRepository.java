package com.furb.web2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.furb.web2.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}