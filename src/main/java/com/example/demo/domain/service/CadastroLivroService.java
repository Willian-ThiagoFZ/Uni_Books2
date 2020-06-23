package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Livro;
import com.example.demo.domain.repository.LivroRepository;

@Service
public class CadastroLivroService {
	
	@Autowired
	private LivroRepository livroRepository;

	public Livro salvar(Livro livro) {
		
		return livroRepository.save(livro);
	}
	
	public void excluir(Long bookId) {
		livroRepository.deleteById(bookId);
	}
}
