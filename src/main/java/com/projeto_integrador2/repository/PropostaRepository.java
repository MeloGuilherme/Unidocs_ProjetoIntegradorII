package com.projeto_integrador2.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto_integrador2.model.Proposta;

@Repository
@Transactional
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

	@Query("select t from Proposta t where t.titulo like %?1%")
    List<Proposta> findPropostaByTitulo(String titulo);
}
