package com.example.ipldashboard.model;

import java.time.LocalDate;

public class MatchBuilder {
	private Match match = new Match();

	public MatchBuilder() {
		match = new Match();
	}
	public Match build() {
		return match;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public MatchBuilder setId(long id) {
		this.match.setId(id);
		return this;
	}
	/**
	 * @param city
	 *            the city to set
	 */
	public MatchBuilder setCity(String city) {
		this.match.setCity(city);
		return this;
	}
	/**
	 * @param date
	 *            the date to set
	 */
	public MatchBuilder setDate(LocalDate date) {
		this.match.setDate(date);
		return this;
	}
	/**
	 * @param playerOfMatch
	 *            the playerOfMatch to set
	 */
	public MatchBuilder setPlayerOfMatch(String playerOfMatch) {
		this.match.setPlayerOfMatch(playerOfMatch);
		return this;
	}
	/**
	 * @param venue
	 *            the venue to set
	 */
	public MatchBuilder setVenue(String venue) {
		this.match.setVenue(venue);
		return this;
	}
	/**
	 * @param team1
	 *            the team1 to set
	 */
	public MatchBuilder setTeam1(String team1) {
		this.match.setTeam1(team1);
		return this;
	}
	/**
	 * @param team2
	 *            the team2 to set
	 */
	public MatchBuilder setTeam2(String team2) {
		this.match.setTeam2(team2);
		return this;
	}
	/**
	 * @param winner
	 *            the winner to set
	 */
	public MatchBuilder setWinner(String winner) {
		this.match.setWinner(winner);
		return this;
	}
	/**
	 * @param result
	 *            the result to set
	 */
	public MatchBuilder setResult(String result) {
		this.match.setResult(result);
		return this;
	}
	/**
	 * @param resultMargin
	 *            the resultMargin to set
	 */
	public MatchBuilder setResultMargin(String resultMargin) {
		this.match.setResultMargin(resultMargin);
		return this;
	}
	/**
	 * @param umpire1
	 *            the umpire1 to set
	 */
	public MatchBuilder setUmpire1(String umpire1) {
		this.match.setUmpire1(umpire1);
		return this;
	}
	/**
	 * @param umpire2
	 *            the umpire2 to set
	 */
	public MatchBuilder setUmpire2(String umpire2) {
		this.match.setUmpire2(umpire2);
		return this;
	}
	/**
	 * @param tossWinner
	 *            the tossWinner to set
	 */
	public MatchBuilder setTossWinner(String tossWinner) {
		this.match.setTossWinner(tossWinner);
		return this;
	}
	/**
	 * @param tossDecision
	 *            the tossDecision to set
	 */
	public MatchBuilder setTossDecision(String tossDecision) {
		this.match.setTossDecision(tossDecision);
		return this;
	}
}
