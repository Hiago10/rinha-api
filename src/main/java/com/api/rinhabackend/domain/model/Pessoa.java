package com.api.rinhabackend.domain.model;

import java.util.List;
import java.util.UUID;

import com.api.rinhabackend.api.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@NotNull
	@Column(length = 32, unique = true, nullable = false)
	private String apelido;
	
	@NotNull
	@Size(min = 1)
	@Column(length = 100, nullable = false)
	private String nome;
	
	@NotNull
	@Pattern(regexp = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
	@Column(nullable = false)
	private String nascimento;
	
	@Convert(converter = StringListConverter.class)
    @Column(name = "stack", columnDefinition = "text")
    private List<String> stack;
}
