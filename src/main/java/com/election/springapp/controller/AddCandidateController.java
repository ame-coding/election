package com.election.springapp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.election.springapp.model.AddCandidateDto;
import com.election.springapp.service.AddCandidateService;
import com.election.springapp.service.CandidateService;

import jakarta.validation.Valid;

@Controller
public class AddCandidateController {
	
	private static final Logger log = LoggerFactory.getLogger(AddVotersController.class);
	private final AddCandidateService addCandidateService;
	private final CandidateService candidateService;
	
	public AddCandidateController (AddCandidateService addCandidateService, CandidateService candidateService) {
		
		this.addCandidateService=addCandidateService;
		this.candidateService=candidateService;
		
	}
	
	@GetMapping("/addcandidate")
	public String addCandidatePage(Model model) {
		
		model.addAttribute("addCandidateDto", new AddCandidateDto());
		return "addcandidate";
		
	}
	
	
	@GetMapping("/viewcandidates")
	public String viewCandidatesPage(Model model) {
		
		model.addAttribute("viewCandidates", candidateService.showCandidateDetails());
		return "viewcandidates";
		
	}
	
	
	
	
	
	
	
	
	
	@PostMapping("/addcandidate")
	public String addCandidate(
			@Valid @ModelAttribute("addCandidateDto") AddCandidateDto dto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
			) {
		
		if(bindingResult.hasErrors()) {
			return "addcandidate";
		}
		
		try {
			
			addCandidateService.save(dto);
			redirectAttributes.addFlashAttribute("successMessage","Candidate successfully entered");
			return "redirect:/addcandidate";
			
		} catch(IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/addcandidate";
		} catch(DataAccessException e) {
			
			log.error("DB error when saving Candidate: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong, please try again");
			return "redirect:/addcandidate";
		}
		
		
	}
	
	
	
	
	
	
	
}