package com.projeto_integrador2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projeto_integrador2.model.Proposta;
import com.projeto_integrador2.repository.PropostaRepository;

@Controller
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastro_proposta")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_proposta");
		modelAndView.addObject("propostaobj", new Proposta());

		Iterable<Proposta> propostaIt = propostaRepository.findAll();
		modelAndView.addObject("propostas", propostaIt);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST,  value = "**/salvarproposta")
	public ModelAndView salvar(Proposta proposta) {

		propostaRepository.save(proposta);

		ModelAndView andView = new ModelAndView("cadastro/cadastro_proposta");
		Iterable<Proposta> propostaIt = propostaRepository.findAll();

		andView.addObject("propostas", propostaIt);
		andView.addObject("propostaobj", new Proposta());

		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listapropostas")
	public ModelAndView propostas() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastro_proposta");
		Iterable<Proposta> propostaIt = propostaRepository.findAll();
		
		andView.addObject("propostas", propostaIt);
		andView.addObject("propostaobj", new Proposta());
		
		return andView;
	}
	
	@GetMapping("/editarprop/{idprop}")
	public ModelAndView editar(@PathVariable("idprop") Long idprop) {
		
		Optional<Proposta> proposta = propostaRepository.findById(idprop);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_proposta");
		modelAndView.addObject("propostaobj", proposta.get());
		
		return modelAndView;
	}
	
	@GetMapping("/removerprop/{idprop}")
	public ModelAndView excluir(@PathVariable("idprop") Long idprop) {
		
		propostaRepository.deleteById(idprop);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_proposta");
		modelAndView.addObject("propostas", propostaRepository.findAll());
		modelAndView.addObject("propostaobj", new Proposta());
		
		return modelAndView;
	}
	
	@PostMapping("**/pesquisarprop")
	public ModelAndView pesquisar(@RequestParam("titulopesquisa") String titulopesquisa){
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_proposta");
		modelAndView.addObject("propostas", propostaRepository.findPropostaByTitulo(titulopesquisa));
		modelAndView.addObject("propostaobj", new Proposta());
		
		return modelAndView;
	}
	
	@GetMapping("/detalhes_processo/{idproposta}")
	public ModelAndView detalhes(@PathVariable("idproposta") Long idproposta) {

		Optional<Proposta> proposta = propostaRepository.findById(idproposta);

		ModelAndView modelAndView = new ModelAndView("cadastro/detalhes_processo");
		modelAndView.addObject("propostaobj", proposta.get());

		return modelAndView;
	}
}
