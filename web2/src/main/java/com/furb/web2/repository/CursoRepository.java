package com.furb.web2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.furb.web2.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}