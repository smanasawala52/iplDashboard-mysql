package com.example.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.ipldashboard.service.VenueService;

@RestController
public class VenueController {

	@Autowired
	private VenueService venueService;

	@GetMapping("/venues")
	public ModelAndView getVenues() {
		ModelAndView modelAndView = new ModelAndView("venues");
		modelAndView.addObject("venues", venueService.getVenues());
		return modelAndView;
	}
	@GetMapping("/city")
	public ModelAndView getCities() {
		ModelAndView modelAndView = new ModelAndView("cities");
		modelAndView.addObject("cities", venueService.getCities());
		return modelAndView;
	}

	@GetMapping("/venue/{venueName}")
	public ModelAndView getVenueDetailsByName(
			@PathVariable("venueName") String venueName,
			@RequestParam(defaultValue = "0", name = "cp", required = false) int cp) {
		ModelAndView modelAndView = new ModelAndView("venueDetails");
		if(cp<=0) {
			cp=0;
		}
		modelAndView.addObject("pageSize", 10);
		modelAndView.addObject("venue",
				venueService.getVenueByName(venueName, cp));
		modelAndView.addObject("pageBaseUrl", "/venue/" + venueName);
		return modelAndView;
	}
	@GetMapping("/city/{cityName}")
	public ModelAndView getVenuesByCity(
			@PathVariable("cityName") String cityName) {
		ModelAndView modelAndView = new ModelAndView("venues");
		modelAndView.addObject("venues",
				venueService.getVenuesByCity(cityName));
		return modelAndView;
	}

}
