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

import com.projeto_integrador2.model.AreaConhecimento;
import com.projeto_integrador2.repository.AreaConhecimentoRepository;

@Controller
public class AreaConhecimentoController {
	
	@Autowired
	private AreaConhecimentoRepository area_conRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastroarea_con")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroarea_con");
		modelAndView.addObject("area_conobj", new AreaConhecimento());
		
		Iterable<AreaConhecimento> areas_conIt = area_conRepository.findAll();
		modelAndView.addObject("areas_con", areas_conIt); 
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvararea_con")
	public ModelAndView salvar(AreaConhecimento area_con) {
		
		area_conRepository.save(area_con);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastroarea_con");
		Iterable<AreaConhecimento> area_conIt = area_conRepository.findAll();
		
		andView.addObject("areas_con", area_conIt);
		andView.addObject("area_conobj", new AreaConhecimento());
		
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listaareas_con")
	public ModelAndView areas_con() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastroarea_con");
		Iterable<AreaConhecimento> area_conIt = area_conRepository.findAll();
		
		andView.addObject("areas_con", area_conIt);
		andView.addObject("area_conobj", new AreaConhecimento());
		
		return andView;
	}
	
	@GetMapping("/editararea_con/{idarea_con}")
	public ModelAndView editar(@PathVariable("idarea_con") Long idarea_con) {
		
		Optional<AreaConhecimento> area_con = area_conRepository.findById(idarea_con);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroarea_con");
		modelAndView.addObject("area_conobj", area_con.get());
		
		return modelAndView;
	}
	
	@GetMapping("/removerarea_con/{idarea_con}")
	public ModelAndView excluir(@PathVariable("idarea_con") Long idarea_con) {
		
		area_conRepository.deleteById(idarea_con);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroarea_con");
		modelAndView.addObject("areas_con", area_conRepository.findAll());
		modelAndView.addObject("area_conobj", new AreaConhecimento());
		
		return modelAndView;
	}
	
	@PostMapping("**/pesquisararea_con")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa){
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroarea_con");
		modelAndView.addObject("areas_con", area_conRepository.findAreaConhecimentoByNome(nomepesquisa));
		modelAndView.addObject("area_conobj", new AreaConhecimento());
		
		return modelAndView;
	}

}
