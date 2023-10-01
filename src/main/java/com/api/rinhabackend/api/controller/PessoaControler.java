package com.api.rinhabackend.api.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rinhabackend.domain.model.Pessoa;
import com.api.rinhabackend.domain.repository.PessoaRepository;
import com.api.rinhabackend.domain.service.CadastroPessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaControler {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ResponseEntity<Pessoa> adicionar(@RequestBody @Valid Pessoa pessoa) {
		Pessoa pessoaSalva = cadastroPessoaService.salvar(pessoa);
		URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(pessoaSalva.getId())
	            .toUri();
	    return ResponseEntity.created(location).body(pessoaSalva);
	}
	
	@GetMapping("/{id}")
	private Pessoa detalhar(@PathVariable UUID id) {
		return cadastroPessoaService.buscarOuFalhar(id);
	}
		
	@GetMapping
	private ResponseEntity<List<Pessoa>> buscarPorTermo(@RequestParam(name = "t", required = true) String termo) {
		if(termo.isBlank()) {
			return ResponseEntity.badRequest().build();
	    }
		
		return ResponseEntity.ok(pessoaRepository.findAllBySearchTerm(termo));
	}
	
	@GetMapping("/contagem-pessoas")
	private Long contagemPessoas() {
		return pessoaRepository.count();
	}
}
