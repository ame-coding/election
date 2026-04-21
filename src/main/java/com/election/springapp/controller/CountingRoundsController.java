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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.election.springapp.model.CountingRounds;
import com.election.springapp.service.CountingRoundsService;

import jakarta.validation.Valid;

@Controller
public class CountingRoundsController {
	
	private static final Logger log = LoggerFactory.getLogger(CountingRoundsController.class);
	private final CountingRoundsService service;
	
	public CountingRoundsController(CountingRoundsService service) {
		
		this.service=service;
		
	}
	
	@GetMapping("/countingrounds")
	public String countingroundsPage(Model model) {
		
		model.addAttribute("countingrounds", new CountingRounds());
		
		return "countingrounds";
		
	}
	
	@PostMapping("/countingrounds")
	public String countingrounds(
			@Valid @ModelAttribute("countingrounds") CountingRounds dto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model
			){
		
		if(bindingResult.hasErrors()) {
			return "countingrounds";
		}
		
		try {
			
			service.save(dto);
			redirectAttributes.addFlashAttribute("successMessage", "Counting round " + dto.getRoundno() + " successfully added for Assembly Constituency");
			return "redirect:/countingrounds";
			
		} catch (IllegalArgumentException e) {
			
			model.addAttribute("errorMessage", e.getMessage());
			return "countingrounds";
			
		} catch (DataAccessException e) {
			
			log.error("DB error when adding Counting Round for AC: ", e);
			model.addAttribute("errorMessage", "Something went wrong, please try again");
			return "countingrounds";
			
		}
		
	}
	
	
	@GetMapping("/countingrounds/next-no")
	@ResponseBody
	public Long getNextNo(@RequestParam Long acno) {
		
		return service.getNextRoundNo(acno);
		
	}
	
	
}