package com.codingdojo.driverslicense.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.driverslicense.models.License;
import com.codingdojo.driverslicense.repositories.LicenseRepository;

@Service
public class LicenseService {
	
	private final LicenseRepository licenseRepository;
	
	public LicenseService(LicenseRepository licenseRepository) {
		this.licenseRepository = licenseRepository;
	}
	
	public License addLicense(License license) {
		return licenseRepository.save(license);
	}
	
	public static int licenseNumber;

	public static int getLicenseNumber() {
		return licenseNumber;
	}

	public static void setLicenseNumber(int licenseNumber) {
		LicenseService.licenseNumber = licenseNumber;
	}
	
	public Optional<License> retrieveLicenseNumber(Long id) {
		return licenseRepository.findById(id);
	}
	
	public String formattedLicenseNumber(int num) {
		String format = String.format("%06d", num);
		return format;
	}
	
	public String generateLicenseNumber() {
		if(licenseRepository.findTopByOrderByNumberDesc().isEmpty()) {
			licenseNumber += 1;
			return formattedLicenseNumber(licenseNumber);
		}
		else {
			List<License> top = licenseRepository.findTopByOrderByNumberDesc();
			licenseNumber = 1+(Integer.parseInt(top.get(0).getNumber()));
			return formattedLicenseNumber(licenseNumber);
		}
	}

}
