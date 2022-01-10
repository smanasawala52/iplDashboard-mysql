package com.example.ipldashboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.ipldashboard.model.ITeamCount;
import com.example.ipldashboard.model.Team;
import com.example.ipldashboard.model.TeamBuilder;
import com.example.ipldashboard.repository.MatchRepository;
import com.example.ipldashboard.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private TeamRepository teamRepository;

	public List<Team> getTeams(List<ITeamCount> objs1, List<ITeamCount> objs2) {
		List<Team> t1 = objs1.stream().map(x -> new TeamBuilder()
				.setName(x.getTeamName()).setTotalMatches(x.getTotalMatches())
				.setTotalWins(x.getTotalWins())
				.setTotalBatFirst(x.getTotalBatFirst())
				.setTotalFieldFirst(x.getTotalFieldFirst())
				.setTotalNoResult(x.getTotalNoResult())
				.setTotalTies(x.getTotalTies())
				.setTotalTossWinBatFirst(x.getTotalTossWinBatFirst())
				.setTotalTossWinFieldFirst(x.getTotalTossWinFieldFirst())
				.setTotalTossWins(x.getTotalTossWins())
				.setTotalWins(x.getTotalWins())
				.setTotalWinsBatFirst(x.getTotalWinsBatFirst())
				.setTotalWinsFieldFirst(x.getTotalWinsFieldFirst())
				.setTotalWinsByRuns(x.getTotalWinsByRuns())
				.setTotalWinsByWikets(x.getTotalWinsByWikets()).build())
				.collect(Collectors.toList());
		List<Team> t2 = objs2.stream().map(x -> new TeamBuilder()
				.setName(x.getTeamName()).setTotalMatches(x.getTotalMatches())
				.setTotalWins(x.getTotalWins())
				.setTotalBatFirst(x.getTotalBatFirst())
				.setTotalFieldFirst(x.getTotalFieldFirst())
				.setTotalNoResult(x.getTotalNoResult())
				.setTotalTies(x.getTotalTies())
				.setTotalTossWinBatFirst(x.getTotalTossWinBatFirst())
				.setTotalTossWinFieldFirst(x.getTotalTossWinFieldFirst())
				.setTotalTossWins(x.getTotalTossWins())
				.setTotalWins(x.getTotalWins())
				.setTotalWinsBatFirst(x.getTotalWinsBatFirst())
				.setTotalWinsFieldFirst(x.getTotalWinsFieldFirst())
				.setTotalWinsByRuns(x.getTotalWinsByRuns())
				.setTotalWinsByWikets(x.getTotalWinsByWikets()).build())
				.collect(Collectors.toList());
		// System.out.println(t1 + "" + t2);
		List<Team> teams = new ArrayList<>(
				Stream.of(t1, t2).flatMap(List::stream).collect(Collectors
						.toMap(Team::getName, d -> d, (Team x, Team y) -> {
							x.setTotalBatFirst(x.getTotalBatFirst()
									+ y.getTotalBatFirst());
							x.setTotalFieldFirst(x.getTotalFieldFirst()
									+ y.getTotalFieldFirst());
							x.setTotalMatches(
									x.getTotalMatches() + y.getTotalMatches());
							x.setTotalNoResult(x.getTotalNoResult()
									+ y.getTotalNoResult());
							x.setTotalTies(x.getTotalTies() + y.getTotalTies());
							x.setTotalTossWinBatFirst(
									x.getTotalTossWinBatFirst()
											+ y.getTotalTossWinBatFirst());
							x.setTotalTossWinFieldFirst(
									x.getTotalTossWinFieldFirst()
											+ y.getTotalTossWinFieldFirst());
							x.setTotalTossWins(x.getTotalTossWins()
									+ y.getTotalTossWins());
							x.setTotalWins(x.getTotalWins() + y.getTotalWins());
							x.setTotalWinsBatFirst(x.getTotalWinsBatFirst()
									+ y.getTotalWinsBatFirst());
							x.setTotalWinsFieldFirst(x.getTotalWinsFieldFirst()
									+ y.getTotalWinsFieldFirst());
							x.setTotalWinsByRuns(x.getTotalWinsByRuns()
									+ y.getTotalWinsByRuns());
							x.setTotalWinsByWikets(x.getTotalWinsByWikets()
									+ y.getTotalWinsByWikets());
							x.getTeams().addAll(y.getTeams());
							x.setTotalWinsPercent(populateWinsPercent(
									x.getTotalWins(), x.getTotalMatches()));
							return x;
						})).values());;
		return teams;
	}

	public List<Team> getTeams() {
		return teamRepository.findAllByOrderByNameAsc();
	}

	public List<Team> getTeamByName1AndName2(String teamName1, String teamName2,
			int cp, String venue, int pageSize) {
		System.out.println("Venue: " + venue + " teamName1: " + teamName1
				+ " teamName2: " + teamName2);
		if (!venue.isEmpty()) {
			List<ITeamCount> objs1 = matchRepository
					.countTotalMatchesByTeam1AndTeam2AndVenue(teamName1,
							teamName2, venue);
			List<ITeamCount> objs2 = matchRepository
					.countTotalMatchesByTeam2AndTeam1AndVenue(teamName2,
							teamName1, venue);
			List<Team> teams = getTeams(objs1, objs2);
			teams = teams.stream().map(team -> {
				team.setMatches(matchRepository
						.getByVenueAndTeam1AndTeam2OrVenueAndTeam2AndTeam1OrderByDateDesc(
								venue, teamName1, teamName2, venue, teamName1,
								teamName2, PageRequest.of(cp, pageSize)));
				return team;
			}).collect(Collectors.toList());

			// Get team 2 data as well
			List<ITeamCount> objs1Team2 = matchRepository
					.countTotalMatchesByTeam1AndTeam2AndVenue(teamName2,
							teamName1, venue);
			List<ITeamCount> objs2Team2 = matchRepository
					.countTotalMatchesByTeam2AndTeam1AndVenue(teamName1,
							teamName2, venue);
			List<Team> teams2 = getTeams(objs1Team2, objs2Team2);
			if (teams != null && teams2 != null) {
				teams.addAll(teams2);
			}
			System.out.println(teams2);
			return teams;
		} else {
			List<ITeamCount> objs1 = matchRepository
					.countTotalMatchesByTeam1AndTeam2(teamName1, teamName2);
			List<ITeamCount> objs2 = matchRepository
					.countTotalMatchesByTeam2AndTeam1(teamName2, teamName1);

			List<Team> teams = getTeams(objs1, objs2);
			teams = teams.stream().map(team -> {
				List<String> lstVenues = matchRepository.findCity(teamName1,
						teamName2, teamName2, teamName1);
				// Collections.sort(lstVenues);
				System.out.println("Shabbir: " + lstVenues);
				team.getVenues().addAll(lstVenues);
				team.setMatches(matchRepository
						.getByTeam1AndTeam2OrTeam2AndTeam1OrderByDateDesc(
								teamName1, teamName2, teamName1, teamName2,
								PageRequest.of(cp, pageSize)));
				return team;
			}).collect(Collectors.toList());

			// Get team 2 data as well
			List<ITeamCount> objs1Team2 = matchRepository
					.countTotalMatchesByTeam1AndTeam2(teamName2, teamName1);
			List<ITeamCount> objs2Team2 = matchRepository
					.countTotalMatchesByTeam2AndTeam1(teamName1, teamName2);
			List<Team> teams2 = getTeams(objs1Team2, objs2Team2);
			if (teams != null && teams2 != null) {
				teams.addAll(teams2);
			}

			System.out.println(teams);
			return teams;
		}
	}
	public List<Team> getTeamByName1(String teamName1, int cp, String venue,
			int pageSize) {
		System.out.println("Venue: " + venue);
		final List<Team> teamsAll = getTeams();
		if (!venue.isEmpty()) {
			List<ITeamCount> objs1 = matchRepository
					.countTotalMatchesByTeam1AndVenue(teamName1, venue);
			List<ITeamCount> objs2 = matchRepository
					.countTotalMatchesByTeam2AndVenue(teamName1, venue);
			List<Team> teams = getTeams(objs1, objs2);
			teams = teams.stream().map(team -> {
				team.setMatches(matchRepository
						.getByVenueAndTeam1OrVenueAndTeam2OrderByDateDesc(venue,
								teamName1, venue, teamName1,
								PageRequest.of(cp, pageSize)));
				teamsAll.stream()
						.filter(x -> !x.getName().equalsIgnoreCase(teamName1))
						.forEach(x -> {
							List<Team> temp = getTeamByName1AndName2(teamName1,
									x.getName(), 0, venue, 3);
							if (temp != null && !temp.isEmpty()) {
								temp.get(0).setName(x.getName());
								team.getTeams().addAll(temp);
							}
						});
				return team;
			}).collect(Collectors.toList());

			System.out.println(teams);
			return teams;
		} else {
			List<ITeamCount> objs1 = matchRepository
					.countTotalMatchesByTeam1(teamName1);
			List<ITeamCount> objs2 = matchRepository
					.countTotalMatchesByTeam2(teamName1);
			List<Team> teams = getTeams(objs1, objs2);
			teams = teams.stream().map(team -> {
				team.setMatches(matchRepository
						.getByTeam1OrTeam2OrderByDateDesc(teamName1, teamName1,
								PageRequest.of(cp, pageSize)));
				List<String> lstV = matchRepository.findCity(teamName1,
						teamName1);
				// Collections.sort(lstV);
				System.out.println("Shabbir: " + lstV);
				team.getVenues().addAll(lstV);
				teamsAll.stream()
						.filter(x -> !x.getName().equalsIgnoreCase(teamName1))
						.forEach(x -> {
							List<Team> temp = getTeamByName1AndName2(teamName1,
									x.getName(), 0, venue, 3);
							if (temp != null && !temp.isEmpty()) {
								temp.get(0).setName(x.getName());
								team.getTeams().addAll(temp);
							}
						});
				return team;
			}).collect(Collectors.toList());
			return teams;
		}
	}

	private double populateWinsPercent(long totalWins, long totalMatches) {
		double totalWinsPercent = 0;
		if (totalWins > 0 && totalMatches > 0) {
			totalWinsPercent = (totalWins * 100 / totalMatches);
		}
		totalWinsPercent = (((int) totalWinsPercent * 100) / 100);
		return totalWinsPercent;
	}

}
