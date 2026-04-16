package com.election.springapp.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.election.springapp.model.AddPartyDto;
import com.election.springapp.model.Party;
import com.election.springapp.service.AddPartyService;
import com.election.springapp.service.PartyService;

import jakarta.validation.Valid;

@Controller
public class PartyController {
	
	private static final Logger log = LoggerFactory.getLogger(AddVotersController.class);
	private final AddPartyService addPartyService;
	private final PartyService partyService;
	
	public PartyController(AddPartyService addPartyService, PartyService partyService) {
		this.addPartyService=addPartyService;
		this.partyService=partyService;
		
	}
	
	@GetMapping("/addparty")
	public String addPartyPage(Model model) {
		
		model.addAttribute("addPartyDto", new AddPartyDto());
		return "addparty";
		
	}
	
	@GetMapping("/viewparties")
	public String partyPage(Model model) {
		
		model.addAttribute("viewparties", partyService.getPartyDetails());
		return "viewparties";
		
	}
	
	
	@GetMapping("/parties")
	@ResponseBody
	public List<Party> getParties(){
		
		return partyService.getParties();
		
	}
	
	
	@GetMapping("/parties/{code}/image")
	@ResponseBody
	public ResponseEntity<byte[]> getImage(@PathVariable long code) {
	
		byte[] symbol = partyService.findImageByCode(code).map(Party::getSymbol).orElse(null);
		
		if (symbol == null) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(symbol);
		
	}
	
	
	
	
	@PostMapping("/addparty")
	public String addParty(
			@Valid @ModelAttribute("addPartyDto") AddPartyDto dto,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			return "addparty";
		}
		
		if(dto.getSymbol() == null || dto.getSymbol().isEmpty()) {
			
			model.addAttribute("fileError", "Please upload a symbol image");
			return "addparty";
			
		}
		
		try {
			
			addPartyService.save(dto);
			
		} catch (IllegalArgumentException e) {
			
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/addparty";
			
		} catch (IOException e) {
			
			model.addAttribute("fileError", "Failed to process image");
			return "addparty";
			
		} catch(DataAccessException e) {
			
			log.error("DB error when saving Party: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong, please try again");
			return "redirect:/addparty";
		}
		
		
		redirectAttributes.addFlashAttribute("successMessage", "Party successfully saved");
		return "redirect:/addparty";
		
	}
	
	
}
