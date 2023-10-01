package com.api.rinhabackend.domain.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.api.rinhabackend.domain.exception.UnprocessableEntityException;
import com.api.rinhabackend.domain.model.Pessoa;
import com.api.rinhabackend.domain.repository.PessoaRepository;

@Service
public class CadastroPessoaService {
	
	private static final String MSG_ENTIDADE_NAO_PROCESSAVEL= "Os dados fornecidos não atendem aos critérios de validação necessários para continuar o processamento. Por favor, verifique os dados e tente novamente";

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
		try {			
			return pessoaRepository.save(pessoa);
		} catch(DataIntegrityViolationException ex) {
			throw new UnprocessableEntityException(MSG_ENTIDADE_NAO_PROCESSAVEL);
		}
	}
	
	public Pessoa buscarOuFalhar(UUID id) {
		return pessoaRepository.findById(id)
				.orElseThrow(() -> new UnprocessableEntityException(MSG_ENTIDADE_NAO_PROCESSAVEL));
	}
}
