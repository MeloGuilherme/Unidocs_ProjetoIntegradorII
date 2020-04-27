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

import com.projeto_integrador2.model.Status;
import com.projeto_integrador2.repository.StatusRepository;

@Controller
public class StatusController {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastro_status")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_status");
		modelAndView.addObject("statusobj", new Status());
		
		Iterable<Status> statusIt = statusRepository.findAll();
		modelAndView.addObject("statuss", statusIt); 
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarstatus")
	public ModelAndView salvar(Status status) {
		
		statusRepository.save(status);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastro_status");
		Iterable<Status> statusIt = statusRepository.findAll();
		
		andView.addObject("statuss", statusIt);
		andView.addObject("statusobj", new Status());
		
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listastatus")
	public ModelAndView statuss() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastro_status");
		Iterable<Status> statusIt = statusRepository.findAll();
		
		andView.addObject("statuss", statusIt);
		andView.addObject("statusobj", new Status());
		
		return andView;
	}
	
	@GetMapping("/editarstatus/{idstatus}")
	public ModelAndView editar(@PathVariable("idstatus") Long idstatus) {
		
		Optional<Status> status = statusRepository.findById(idstatus);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_status");
		modelAndView.addObject("statusobj", status.get());
		
		return modelAndView;
	}
	
	@GetMapping("/removerstatus/{idstatus}")
	public ModelAndView excluir(@PathVariable("idstatus") Long idstatus) {
		
		statusRepository.deleteById(idstatus);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_status");
		modelAndView.addObject("statuss", statusRepository.findAll());
		modelAndView.addObject("statusobj", new Status());
		
		return modelAndView;
	}
	
	@PostMapping("**/pesquisarstatus")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa){
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_status");
		modelAndView.addObject("statuss", statusRepository.findStatusByNome(nomepesquisa));
		modelAndView.addObject("statusobj", new Status());
		
		return modelAndView;
	}

}
