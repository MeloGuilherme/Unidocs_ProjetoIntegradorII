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

import com.projeto_integrador2.model.Professor;
import com.projeto_integrador2.repository.ProfessorRepository;

@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastro_professor")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_professor");
		modelAndView.addObject("professorobj", new Professor());
		
		Iterable<Professor> professorIt = professorRepository.findAll();
		modelAndView.addObject("professores", professorIt); 
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarprofessor")
	public ModelAndView salvar(Professor professor) {
		
		professorRepository.save(professor);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastro_professor");
		Iterable<Professor> professorIt = professorRepository.findAll();
		
		andView.addObject("professores", professorIt);
		andView.addObject("professorobj", new Professor());
		
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listaprofessores")
	public ModelAndView professores() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastro_professor");
		Iterable<Professor> professorIt = professorRepository.findAll();
		
		andView.addObject("professores", professorIt);
		andView.addObject("professorobj", new Professor());
		
		return andView;
	}
	
	@GetMapping("/editarprofessor/{idprofessor}")
	public ModelAndView editar(@PathVariable("idprofessor") Long idprofessor) {
		
		Optional<Professor> professor = professorRepository.findById(idprofessor);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_professor");
		modelAndView.addObject("professorobj", professor.get());
		
		return modelAndView;
	}
	
	@GetMapping("/removerprofessor/{idprofessor}")
	public ModelAndView excluir(@PathVariable("idprofessor") Long idprofessor) {
		
		professorRepository.deleteById(idprofessor);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_professor");
		modelAndView.addObject("professores", professorRepository.findAll());
		modelAndView.addObject("professorobj", new Professor());
		
		return modelAndView;
	}
	
	@PostMapping("**/pesquisarprofessor")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa){
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastro_professor");
		modelAndView.addObject("professores", professorRepository.findProfessorByNome(nomepesquisa));
		modelAndView.addObject("professorobj", new Professor());
		
		return modelAndView;
	}

}
