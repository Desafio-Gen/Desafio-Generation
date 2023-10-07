package com.generation.cadastroAlunos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.cadastroAlunos.Model.cadastroModel;
import com.generation.cadastroAlunos.Repository.cadastroRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cadastroAluno")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class cadastroController {
	@Autowired
	private cadastroRepository repository;
	
	@GetMapping
	public ResponseEntity<List<cadastroModel>> GetAll(){
		return ResponseEntity.ok(repository.findAll()); 
	}
	@GetMapping ("/{id}")
	public ResponseEntity<cadastroModel> getById(@PathVariable Long id) {
		return repository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/nome/{nome}")
	public ResponseEntity<List<cadastroModel>> GetNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<cadastroModel> postCadastro (@Valid @RequestBody cadastroModel cadastro){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cadastro));
	}
	
	@PutMapping
	public ResponseEntity<cadastroModel> putCadastro (@RequestBody cadastroModel cadastro){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(cadastro));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	

}
