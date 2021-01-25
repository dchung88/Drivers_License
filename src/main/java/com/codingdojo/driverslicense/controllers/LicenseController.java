package com.codingdojo.driverslicense.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.driverslicense.models.License;
import com.codingdojo.driverslicense.models.Person;
import com.codingdojo.driverslicense.services.LicenseService;
import com.codingdojo.driverslicense.services.PersonService;

@Controller
public class LicenseController {
	
	private final LicenseService licenseService;
	
	public LicenseController(LicenseService licenseService) {
		this.licenseService = licenseService;
	}
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/licenses/new")
	public String newLicense(@ModelAttribute("licenseObject") License license, Model model) {
		List<Person> personlist = personService.allPerson();
		model.addAttribute("personlist", personlist);
		ArrayList<String> states = new ArrayList<String>(Arrays.asList("Alaska", "Alabama", "California", "Florida", "New York", "Virginia", "Washington"));
		model.addAttribute("states", states);
		return "DriversLicense/newlicense.jsp";
	}
	
	@RequestMapping(value="/addlicense", method=RequestMethod.POST)
	public String addLicense(@Valid @ModelAttribute("licenseObject") License license, BindingResult result) {
		if(result.hasErrors()) {
			return "DriversLicense/newlicense.jsp";
		}
		else {
			String number = licenseService.generateLicenseNumber();
			license.setNumber(number);
			licenseService.addLicense(license);
			return "redirect:/persons/" +license.getId();
		}
	}

}
