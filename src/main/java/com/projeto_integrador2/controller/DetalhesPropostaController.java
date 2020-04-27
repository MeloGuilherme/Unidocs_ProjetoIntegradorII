package com.projeto_integrador2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.projeto_integrador2.model.Proposta;
import com.projeto_integrador2.repository.PropostaRepository;

@Controller
public class DetalhesPropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@RequestMapping("/detalhes_proposta")
	public String det_prop() {
		return "detalhes_processo";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/detalhes_processo")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("cadastro/detalhes_processo");
		modelAndView.addObject("propostaobj", new Proposta());

		Iterable<Proposta> propostaIt = propostaRepository.findAll();
		modelAndView.addObject("propostas", propostaIt);

		return modelAndView;
	}

}
