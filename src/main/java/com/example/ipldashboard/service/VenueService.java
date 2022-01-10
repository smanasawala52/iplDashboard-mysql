package com.example.ipldashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.ipldashboard.model.Venue;
import com.example.ipldashboard.repository.MatchRepository;
import com.example.ipldashboard.repository.VenueRepository;

@Service
public class VenueService {
	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private VenueRepository venueRepository;

	private int PAGE_SIZE = 10;

	public List<Venue> getVenues() {
		return venueRepository.findAll();
	}

	public List<String> getCities() {
		return venueRepository.findCity();
		// Venue venue = venueRepository.getBy();
		// if (venue != null) {
		// venue.setMatches(matchRepository.getByVenueOrderByDateDesc(
		// venueName, PageRequest.of(cp, PAGE_SIZE)));
		// }
		// return venue;
	}

	public Venue getVenueByName(String venueName, int cp) {
		Venue venue = venueRepository.getByName(venueName);
		if (venue != null) {
			venue.setMatches(matchRepository.getByVenueOrderByDateDesc(
					venueName, PageRequest.of(cp, PAGE_SIZE)));
		}
		return venue;
	}
	public List<Venue> getVenuesByCity(String cityName) {
		List<Venue> venues = venueRepository.getByCity(cityName);
		return venues;
	}

}
