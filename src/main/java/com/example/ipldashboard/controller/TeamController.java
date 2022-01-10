package com.example.ipldashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.ipldashboard.model.Team;
import com.example.ipldashboard.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;

	@GetMapping("/teams")
	public ModelAndView getTeams() {
		ModelAndView modelAndView = new ModelAndView("teams");
		modelAndView.addObject("teams", teamService.getTeams());
		return modelAndView;
	}
	@GetMapping("/teams/{teamName1}/{teamName2}")
	public ModelAndView getTeamByName1AndName2(
			@PathVariable("teamName1") String teamName1,
			@PathVariable("teamName2") String teamName2,
			@RequestParam(defaultValue = "0", name = "cp", required = false) int cp,
			@RequestParam(defaultValue = "", name = "venue", required = false) String venue) {
		ModelAndView modelAndView = new ModelAndView("teamDetails");
		if(cp<=0) {
			cp=0;
		}
		modelAndView.addObject("pageSize", 10);
		modelAndView.addObject("teams", teamService
				.getTeamByName1AndName2(teamName1, teamName2, cp, venue, 10));
		if (!venue.isEmpty()) {
			modelAndView.addObject("venue", venue);
		}
		if (!teamName1.isEmpty()) {
			modelAndView.addObject("teamName1", teamName1);
		}
		if (!teamName1.isEmpty()) {
			modelAndView.addObject("teamName2", teamName2);
		}
		modelAndView.addObject("pageBaseUrl",
				"/teams/" + teamName1 + "/" + teamName2 + "?venue=" + venue);

		return modelAndView;
	}
	@GetMapping("/teams/{teamName1}")
	public ModelAndView getTeamByName1(
			@PathVariable("teamName1") String teamName1,
			@RequestParam(defaultValue = "0", name = "cp", required = false) int cp,
			@RequestParam(defaultValue = "", name = "venue", required = false) String venue) {
		ModelAndView modelAndView = new ModelAndView("teamDetails");
		List<Team> teams = teamService.getTeamByName1(teamName1, cp, venue, 10);
		modelAndView.addObject("teams", teams);
		if(cp<=0) {
			cp=0;
		}
		modelAndView.addObject("pageSize", 10);
		if (!teamName1.isEmpty()) {
			modelAndView.addObject("teamName1", teamName1);
		}
		if (!venue.isEmpty()) {
			modelAndView.addObject("venue", venue);
		}
		modelAndView.addObject("pageBaseUrl",
				"/teams/" + teamName1 + "?venue=" + venue);

		return modelAndView;
	}

}
