package com.api.rinhabackend.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.rinhabackend.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID>{
	
//	@Query(
//		       nativeQuery = true,
//		       value = "SELECT DISTINCT p.*, ps.* FROM pessoa p " +
//		       "LEFT JOIN pessoa_stack ps ON p.id = ps.pessoa_id " +
//		       "WHERE p.apelido LIKE CONCAT('%', :termo, '%') OR " +
//		       "p.nome LIKE CONCAT('%', :termo, '%') OR " +
//		       "ps.stack LIKE CONCAT('%', :termo, '%') " +
//		       "LIMIT 50"
//		   )
	@Query(nativeQuery = true, value = "SELECT * FROM pessoa p WHERE p.nome LIKE %:termo% OR p.apelido LIKE %:termo% OR p.stack LIKE %:termo%")
	List<Pessoa> findAllBySearchTerm(@Param("termo") String termo);
}
