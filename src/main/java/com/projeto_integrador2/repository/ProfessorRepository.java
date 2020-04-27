package com.projeto_integrador2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto_integrador2.model.Professor;

@Repository
@Transactional
public interface ProfessorRepository extends CrudRepository<Professor, Long>{
	
	@Query("select p from Professor p where p.nome like %?1%")
	List<Professor> findProfessorByNome(String nome);

}
