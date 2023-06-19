package com.skill.tracker.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.tracker.model.Profile;
import com.skill.tracker.service.ProfileService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/engineer")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@GetMapping("/admin/{criteria}/{criteriaValue}")
	public ResponseEntity<List<Profile>> getProfiles(@PathVariable("criteria") String criteria,@PathVariable("criteriaValue") String criteriaValue ) {
		try {
			profileService.getProfile(criteria,criteriaValue);
			
			return new ResponseEntity<>(profileService.getProfile(criteria,criteriaValue), HttpStatus.OK);
			

			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/add-profile")
	public ResponseEntity<Profile> addProfile(@RequestBody Profile profile) {

		try {
			return profileService.createProfile(profile);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-profile/{userId}")
	public ResponseEntity<Profile> updateTutorial(@PathVariable("userId") UUID id,
			@RequestBody Profile updatedProfile) {

		Optional<Profile> profile = profileService.getProfile(id);

		if (profile.isPresent()) {
			return profileService.updateProfile(updatedProfile);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
