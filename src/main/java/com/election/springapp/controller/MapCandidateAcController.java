package com.election.springapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.election.springapp.model.MapCandidateAcDto;
import com.election.springapp.service.MapCandidateAcService;

import jakarta.validation.Valid;

@Controller
public class MapCandidateAcController {
	
	private static final Logger log = LoggerFactory.getLogger(MapCandidateAcController.class);
	private final MapCandidateAcService mapCandidateAcService;
	
	public MapCandidateAcController (MapCandidateAcService mapCandidateAcService) {
		
		this.mapCandidateAcService=mapCandidateAcService;
		
	}
	
	
	@GetMapping("mapcandidateac")
	public String mapCandidateAcPage(Model model) {
		
		model.addAttribute("mapCandidateAcDto", new MapCandidateAcDto());
		model.addAttribute("viewCandidatesAcMap", mapCandidateAcService.findAll());
		
		return "mapcandidateac";
		
	}
	
	
	
	
	@PostMapping("mapcandidateac")
	public String mapCandidateAc(
			@Valid @ModelAttribute ("mapCandidateAcDto") MapCandidateAcDto dto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return "mapcandidateac";
		}
		
		try {
			
			mapCandidateAcService.save(dto);
			redirectAttributes.addFlashAttribute("successMessage", "Candidate successfully mapped to Assembly Constituency");
			return "redirect:/mapcandidateac";
			
		} catch (IllegalArgumentException e) {
			
			model.addAttribute("errorMessage", e.getMessage());
			return "mapcandidateac";
		
		} catch (DataAccessException e) {
			
			log.error("DB error when mapping Candidate to AC: ", e);
			model.addAttribute("errorMessage", "Something went wrong, please try again");
			return "mapcandidateac";
			
		}
		
		
		
	}
	
	@PostMapping("mapcandidateac/delete/{code}/{acno}")
	public String deleteCandidateByCodeAndAcno(@PathVariable Long code, @PathVariable Long acno) {
		
		mapCandidateAcService.deleteByCandidatecodeAndAcno(code, acno);
		return "redirect:/mapcandidateac";
		
	}
	
	
	
}