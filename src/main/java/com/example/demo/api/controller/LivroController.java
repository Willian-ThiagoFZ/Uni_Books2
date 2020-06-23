package com.example.demo.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Livro;
import com.example.demo.domain.repository.LivroRepository;
import com.example.demo.domain.service.CadastroLivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CadastroLivroService cadastroLivro;
	
	@GetMapping
	public List<Livro> listar() {
		return livroRepository.findAll();
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<Livro> buscar(@PathVariable Long bookId) {
		Optional<Livro> livro = livroRepository.findById(bookId);
		
		if(livro.isPresent()) {
			return ResponseEntity.ok(livro.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Livro adicionar(@Valid @RequestBody Livro livro) {
		return cadastroLivro.salvar(livro);
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<Livro> alterar(@Valid @PathVariable Long bookId, @RequestBody Livro livro){
		
		if(!livroRepository.existsById(bookId)) {
			return ResponseEntity.notFound().build();
		}
		
		livro.setId(bookId);
		livro = cadastroLivro.salvar(livro);
		
		return ResponseEntity.ok(livro);
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> remover(@PathVariable Long bookId){
		
		
		if(!livroRepository.existsById(bookId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroLivro.excluir(bookId);
		
		return ResponseEntity.noContent().build();
	}
	
}
