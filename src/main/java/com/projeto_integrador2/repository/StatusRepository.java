package com.projeto_integrador2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto_integrador2.model.Status;

@Repository
@Transactional
public interface StatusRepository extends CrudRepository<Status, Long> {
	
	@Query("select s from Status s where s.nome like %?1%")
	List<Status> findStatusByNome(String nome);

}
