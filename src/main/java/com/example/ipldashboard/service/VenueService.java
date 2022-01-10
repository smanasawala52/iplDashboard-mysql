package com.example.ipldashboard.service;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.ipldashboard.model.ICity;
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

	public Map<String, SortedSet<String>> getCities() {
		Map<String, SortedSet<String>> cities = new TreeMap<>();
		List<ICity> temp = venueRepository.findCity();
		for (ICity icity : temp) {
			if (cities.get(icity.getCity()) != null) {
				cities.get(icity.getCity()).add(icity.getVenueName());
			} else {
				SortedSet<String> lst = new TreeSet<String>();
				lst.add(icity.getVenueName());
				cities.put(icity.getCity(), lst);
			}
		}
		// cities = temp.stream()
		// .map(x -> new AbstractMap.SimpleEntry<>(x.getCity(),
		// x.getVenueName()))
		// .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors
		// .mapping(Map.Entry::getValue, Collectors.toSet())));

		System.out.println(cities);
		return cities;
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
