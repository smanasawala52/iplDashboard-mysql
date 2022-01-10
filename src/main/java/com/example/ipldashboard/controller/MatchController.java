package com.example.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.service.MatchService;

@RestController
public class MatchController {

	@Autowired
	private MatchService matchService;

	private int PAGE_SIZE = 10;

	@GetMapping("/matches")
	public ModelAndView getMatches(
			@RequestParam(defaultValue = "0", name = "cp", required = false) int cp) {
		ModelAndView modelAndView = new ModelAndView("matches");
		if(cp<=0) {
			cp=0;
		}
		modelAndView.addObject("pageSize", PAGE_SIZE);
		Page<Match> page = matchService.getMatches(cp, PAGE_SIZE);
		modelAndView.addObject("matches", page);
		modelAndView.addObject("hasNext", page.hasNext());
		modelAndView.addObject("hasPrevious", page.hasPrevious());
		return modelAndView;
	}

	@GetMapping("/matches/{id}")
	public ModelAndView getMatchDetails(@PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView("matchDetails");
		modelAndView.addObject("match", matchService.getMatchDetails(id));
		return modelAndView;
	}
}
