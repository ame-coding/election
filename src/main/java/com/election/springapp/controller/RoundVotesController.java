package com.election.springapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.election.springapp.model.CandidateDetails;
import com.election.springapp.model.VoteSubmissionForm;
import com.election.springapp.service.RoundVotesService;

import jakarta.validation.Valid;


@Controller
public class RoundVotesController {
	
	private static final Logger log=LoggerFactory.getLogger(RoundVotesController.class);
	private final RoundVotesService service;
	
	public RoundVotesController(RoundVotesService service) {
		
		this.service=service;
		
	}
	
	@GetMapping("/roundvotes")
	public String roundvotesPage() {
		
		return "addroundvotes";
		
	}
	
	
	
	@GetMapping("/roundvotes/rounds")
	@ResponseBody
	public List<Integer> getRounds(@RequestParam Long acno){
		
		return service.getRoundsByAcno(acno);
		
	}
	
	@GetMapping("/roundvotes/candidates")
	@ResponseBody
	public List<CandidateDetails> getCandidates(@RequestParam Long acno){
		
		
		return service.getCandidatesByAcno(acno);
		
	}
	
	
	@PostMapping("/roundvotes/submit")
	public String submitVotes(@Valid @ModelAttribute VoteSubmissionForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errorMessage", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/roundvotes?error";
				
		}
		
		try {
			
			service.saveVotes(form);
			redirectAttributes.addFlashAttribute("successMessage", "Votes submitted sucessfully!");
			return "redirect:/roundvotes?success";
			
			
		} catch(DuplicateKeyException e) {
			
			redirectAttributes.addFlashAttribute("errorMessage", "Votes already submitted for this Assembly Constituency's round");
			return "redirect:/roundvotes?error";
			
		} catch(DataAccessException e) {
			
			log.error("DB Error when adding Round wise votes:", e);
			redirectAttributes.addFlashAttribute("errorMessage","Database Error, please try again");
			return "redirect:/roundvotes?error";
			
		}
		
		
	}
	
	
	
}