package com.example.ipldashboard.model;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.domain.Page;

@Entity
public class Team implements Comparable<Team> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private long totalMatches;
	private long totalWins;
	@Transient
	private double totalWinsPercent;
	private long totalTies;
	private long totalNoResult;
	private long totalTossWins;
	private long totalTossWinBatFirst;
	private long totalTossWinFieldFirst;
	private long totalBatFirst;
	private long totalFieldFirst;
	private long totalWinsBatFirst;
	private long totalWinsFieldFirst;
	private long totalWinsByWikets;

	private long totalWinsByRuns;

	@Transient
	private Page<Match> matches;

	@Transient
	private SortedSet<Team> teams = new TreeSet<>();

	@Transient
	private SortedSet<String> venues = new TreeSet<>();

	public Team() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the totalMatches
	 */
	public long getTotalMatches() {
		return totalMatches;
	}

	/**
	 * @param totalMatches
	 *            the totalMatches to set
	 */
	public void setTotalMatches(long totalMatches) {
		this.totalMatches = totalMatches;
	}

	/**
	 * @return the totalWins
	 */
	public long getTotalWins() {
		return totalWins;
	}

	/**
	 * @param totalWins
	 *            the totalWins to set
	 */
	public void setTotalWins(long totalWins) {
		this.totalWins = totalWins;
	}

	/**
	 * @return the totalTies
	 */
	public long getTotalTies() {
		return totalTies;
	}

	/**
	 * @param totalTies
	 *            the totalTies to set
	 */
	public void setTotalTies(long totalTies) {
		this.totalTies = totalTies;
	}

	/**
	 * @return the totalNoResult
	 */
	public long getTotalNoResult() {
		return totalNoResult;
	}

	/**
	 * @param totalNoResult
	 *            the totalNoResult to set
	 */
	public void setTotalNoResult(long totalNoResult) {
		this.totalNoResult = totalNoResult;
	}

	/**
	 * @return the totalTossWins
	 */
	public long getTotalTossWins() {
		return totalTossWins;
	}

	/**
	 * @param totalTossWins
	 *            the totalTossWins to set
	 */
	public void setTotalTossWins(long totalTossWins) {
		this.totalTossWins = totalTossWins;
	}

	/**
	 * @return the totalTossWinBatFirst
	 */
	public long getTotalTossWinBatFirst() {
		return totalTossWinBatFirst;
	}

	/**
	 * @param totalTossWinBatFirst
	 *            the totalTossWinBatFirst to set
	 */
	public void setTotalTossWinBatFirst(long totalTossWinBatFirst) {
		this.totalTossWinBatFirst = totalTossWinBatFirst;
	}

	/**
	 * @return the totalTossWinFieldFirst
	 */
	public long getTotalTossWinFieldFirst() {
		return totalTossWinFieldFirst;
	}

	/**
	 * @param totalTossWinFieldFirst
	 *            the totalTossWinFieldFirst to set
	 */
	public void setTotalTossWinFieldFirst(long totalTossWinFieldFirst) {
		this.totalTossWinFieldFirst = totalTossWinFieldFirst;
	}

	/**
	 * @return the totalBatFirst
	 */
	public long getTotalBatFirst() {
		return totalBatFirst;
	}

	/**
	 * @param totalBatFirst
	 *            the totalBatFirst to set
	 */
	public void setTotalBatFirst(long totalBatFirst) {
		this.totalBatFirst = totalBatFirst;
	}

	/**
	 * @return the totalFieldFirst
	 */
	public long getTotalFieldFirst() {
		return totalFieldFirst;
	}

	/**
	 * @param totalFieldFirst
	 *            the totalFieldFirst to set
	 */
	public void setTotalFieldFirst(long totalFieldFirst) {
		this.totalFieldFirst = totalFieldFirst;
	}

	/**
	 * @return the totalWinsBatFirst
	 */
	public long getTotalWinsBatFirst() {
		return totalWinsBatFirst;
	}

	/**
	 * @param totalWinsBatFirst
	 *            the totalWinsBatFirst to set
	 */
	public void setTotalWinsBatFirst(long totalWinsBatFirst) {
		this.totalWinsBatFirst = totalWinsBatFirst;
	}

	/**
	 * @return the totalWinsFieldFirst
	 */
	public long getTotalWinsFieldFirst() {
		return totalWinsFieldFirst;
	}

	/**
	 * @param totalWinsFieldFirst
	 *            the totalWinsFieldFirst to set
	 */
	public void setTotalWinsFieldFirst(long totalWinsFieldFirst) {
		this.totalWinsFieldFirst = totalWinsFieldFirst;
	}

	/**
	 * @return the totalWinsByWikets
	 */
	public long getTotalWinsByWikets() {
		return totalWinsByWikets;
	}

	/**
	 * @param totalWinsByWikets
	 *            the totalWinsByWikets to set
	 */
	public void setTotalWinsByWikets(long totalWinsByWikets) {
		this.totalWinsByWikets = totalWinsByWikets;
	}

	/**
	 * @return the totalWinsByRuns
	 */
	public long getTotalWinsByRuns() {
		return totalWinsByRuns;
	}

	/**
	 * @param totalWinsByRuns
	 *            the totalWinsByRuns to set
	 */
	public void setTotalWinsByRuns(long totalWinsByRuns) {
		this.totalWinsByRuns = totalWinsByRuns;
	}

	/**
	 * @return the matches
	 */
	public Page<Match> getMatches() {
		return matches;
	}

	/**
	 * @param matches
	 *            the matches to set
	 */
	public void setMatches(Page<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", totalMatches="
				+ totalMatches + ", totalWins=" + totalWins + ", totalTies="
				+ totalTies + ", totalNoResult=" + totalNoResult
				+ ", totalTossWins=" + totalTossWins + ", totalTossWinBatFirst="
				+ totalTossWinBatFirst + ", totalTossWinFieldFirst="
				+ totalTossWinFieldFirst + ", totalBatFirst=" + totalBatFirst
				+ ", totalFieldFirst=" + totalFieldFirst
				+ ", totalWinsBatFirst=" + totalWinsBatFirst
				+ ", totalWinsFieldFirst=" + totalWinsFieldFirst
				+ ", totalWinsByWikets=" + totalWinsByWikets
				+ ", totalWinsByRuns=" + totalWinsByRuns + ", matches="
				+ matches + ", teams=" + teams + ", venues=" + venues + "]";
	}

	/**
	 * @return the teams
	 */
	public SortedSet<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams
	 *            the teams to set
	 */
	public void setTeams(SortedSet<Team> teams) {
		this.teams = teams;
	}

	/**
	 * @return the venues
	 */
	public SortedSet<String> getVenues() {
		return venues;
	}

	/**
	 * @param venues
	 *            the venues to set
	 */
	public void setVenues(SortedSet<String> venues) {
		this.venues = venues;
	}

	@Override
	public int compareTo(Team o) {
		if (this != null && o != null && this.name != null && o.name != null) {
			return this.name.compareTo(o.name);
		}
		return 0;
	}

	/**
	 * @return the totalWinsPercent
	 */
	public double getTotalWinsPercent() {
		return totalWinsPercent;
	}

	/**
	 * @param totalWinsPercent
	 *            the totalWinsPercent to set
	 */
	public void setTotalWinsPercent(double totalWinsPercent) {
		this.totalWinsPercent = totalWinsPercent;
	}
}
