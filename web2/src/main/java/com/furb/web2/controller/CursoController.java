package com.furb.web2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.furb.web2.model.Curso;
import com.furb.web2.repository.CursoRepository;

@RestController
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping("/cursos")
    public List<Curso> listar() {
        return repository.findAll();
    }

    @PostMapping("/cursos")
    public Curso salvar(@RequestBody Curso curso) {
        return repository.save(curso);
    }
}