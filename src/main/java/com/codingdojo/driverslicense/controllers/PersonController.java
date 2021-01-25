package com.codingdojo.driverslicense.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.driverslicense.models.Person;
import com.codingdojo.driverslicense.services.LicenseService;
import com.codingdojo.driverslicense.services.PersonService;

@Controller
public class PersonController {
	
	private final PersonService personService;
	private final LicenseService licenseService;
	
	public PersonController(PersonService personService, LicenseService licenseService) {
		this.personService = personService;
		this.licenseService = licenseService;
	}
	
	@RequestMapping("/persons/new")
	public String newPerson(@ModelAttribute("personObject") Person person, Model model) {
		return "DriversLicense/newperson.jsp";
	}
	
	@RequestMapping(value="/addperson", method=RequestMethod.POST)
	public String addPerson(@Valid @ModelAttribute("personObject") Person person, BindingResult result) {
		if(result.hasErrors()) {
			return "DriversLicense/newperson.jsp";
		}
		else {
			personService.addPerson(person);
			return "redirect:licenses/new";
		}
	}
	
	@RequestMapping("/persons/{id}")
	public String display(@PathVariable("id") Long id, Model model) {
		model.addAttribute("license", licenseService.retrieveLicenseNumber(id).get());
		return "DriversLicense/display.jsp";
	}

}
