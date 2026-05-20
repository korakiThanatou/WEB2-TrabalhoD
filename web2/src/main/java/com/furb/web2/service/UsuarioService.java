package com.furb.web2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.furb.web2.dto.LoginDTO;
import com.furb.web2.model.Usuario;
import com.furb.web2.repository.UsuarioRepository;
import com.furb.web2.security.JwtService;

@Service
public class UsuarioService {
    @Autowired
    private JwtService jwtService;


    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario salvar(Usuario usuario) {

        usuario.setSenha(
            encoder.encode(usuario.getSenha())
        );

        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                    )
                );
    }

    public Usuario atualizar(Long id, Usuario usuario) {

        usuario.setId(id);

        usuario.setSenha(
            encoder.encode(usuario.getSenha())
        );

        return repository.save(usuario);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public String login(LoginDTO dto) {

        Usuario usuario = repository.findByLogin(dto.getLogin());

        if (usuario == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado"
            );
        }

        boolean senhaCorreta = encoder.matches(
            dto.getSenha(),
            usuario.getSenha()
        );

        if (!senhaCorreta) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Senha inválida"
            );
        }

        return jwtService.gerarToken(usuario.getLogin());
    }
}