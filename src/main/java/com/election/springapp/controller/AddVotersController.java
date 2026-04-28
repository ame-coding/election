package com.election.springapp.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.election.springapp.model.AddVotersDto;
import com.election.springapp.service.AddVotersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

@Controller
public class AddVotersController {
	
	private static final Logger log = LoggerFactory.getLogger(AddVotersController.class);
	private final AddVotersService addVotersService;
	
	public AddVotersController(AddVotersService addVotersService) {
		this.addVotersService=addVotersService;
	}
	
	@GetMapping("/addvoters")
	public String addvotersPage(Model model) {
		model.addAttribute("addvotersdto", new AddVotersDto());
		model.addAttribute("votespolled", addVotersService.findAll());
		return "addvoters";
	}

	
	@GetMapping("/votespolled")
	public String votespolledPage(Model model) {
		
		model.addAttribute("votespolled", addVotersService.findAll());
		return "votespolled";
		
		
	}
	
	@PostMapping("/votespolled/delete/{id}")
	public String deleteVotesPolledByAc(@PathVariable Long id) {
		
		addVotersService.deleteById(id);
		return "redirect:/addvoters";
		
	}
	
	
	@PostMapping("/addvoters")
	public String addVoters(
			@Valid @ModelAttribute("votespolled") AddVotersDto dto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "addvoters";
		}
		
		try {
			
			addVotersService.save(dto);
			redirectAttributes.addFlashAttribute("successMessage", "Votes Polled successfully entered");
			return "redirect:/addvoters";
			
		} catch(IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/addvoters";
			
		} catch(DataAccessException e) {
			
			log.error("DB error when saving Votes Polled: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong, please try again");
			return "redirect:/addvoters";
		}
	
		
	}
		
	
	
	
}