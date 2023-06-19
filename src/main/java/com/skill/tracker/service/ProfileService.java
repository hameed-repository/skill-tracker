package com.skill.tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skill.tracker.model.Profile;
import com.skill.tracker.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	ProfileRepository profileRepository;

	public ResponseEntity<Profile> createProfile(Profile profile) {
		Profile _profile = profileRepository.save(profile);
		return new ResponseEntity<>(_profile, HttpStatus.CREATED);

	}

	public Optional<Profile> getProfile(UUID id) {
		return profileRepository.findById(id);
	}

	public ResponseEntity<Profile> updateProfile(Profile updatedProfile) {
		Profile _profile = profileRepository.save(updatedProfile);
		return new ResponseEntity<>(_profile, HttpStatus.CREATED);
	}

	public List<Profile> getProfile(String criteria, String criteriaValue) {
		List<Profile> profiles = new ArrayList<Profile>();
		if (criteria != null) {
			switch (criteria) {
			case "name":

				profileRepository.findByCriteria(criteria).forEach(profiles::add);
			}
		}
		return profiles;

	}
}
