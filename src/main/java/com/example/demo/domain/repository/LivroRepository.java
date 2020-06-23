package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	List<Livro> findByNome(String nome);
	List<Livro> findByNomeContaining(String nome);
}

