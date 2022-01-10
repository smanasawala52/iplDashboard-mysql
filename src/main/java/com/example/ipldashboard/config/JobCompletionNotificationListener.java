package com.example.ipldashboard.config;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.ipldashboard.model.ITeamCount;
import com.example.ipldashboard.model.IVenueCount;
import com.example.ipldashboard.model.MatchBuilder;
import com.example.ipldashboard.model.Team;
import com.example.ipldashboard.model.Venue;
import com.example.ipldashboard.model.VenueBuilder;
import com.example.ipldashboard.repository.MatchRepository;
import com.example.ipldashboard.repository.TeamRepository;
import com.example.ipldashboard.repository.VenueRepository;
import com.example.ipldashboard.service.TeamService;

@Component
public class JobCompletionNotificationListener
		extends
			JobExecutionListenerSupport {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private VenueRepository venueRepository;

	@Autowired
	private TeamService teamService;

	// @Autowired
	// private EntityManager entityManager;

	// @Autowired
	// public JobCompletionNotificationListener(EntityManager pEntityManager) {
	// this.entityManager = pEntityManager;
	// }

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("JOB FINISHED");
			jdbcTemplate
					.query("SELECT * FROM matches", (rs, rn) -> new MatchBuilder()
							.setId(rs.getLong("id"))
							.setCity(rs.getString("city"))
							.setUmpire1(rs.getString("umpire1"))
							.setUmpire2(rs.getString("umpire2"))
							.setDate(Instant
									.ofEpochMilli(rs.getDate("date").getTime())
									.atZone(ZoneId.systemDefault())
									.toLocalDate())
							// LocalDate.ofInstant(input.toInstant(),
							// ZoneId.systemDefault())
							.setTeam1(rs.getString("team1"))
							.setTeam2(rs.getString("team2"))
							.setWinner(rs.getString("winner"))
							.setResult(rs.getString("result"))
							.setResultMargin(rs.getString("result_margin"))
							.setPlayerOfMatch(rs.getString("player_of_match"))
							.setVenue(rs.getString("venue")).build())
					.forEach(match -> System.out.println("" + match));

			List<ITeamCount> objs = matchRepository.countTotalMatchesByTeam1();
			List<ITeamCount> objs2 = matchRepository.countTotalMatchesByTeam2();
			List<Team> teams = teamService.getTeams(objs, objs2);
			System.out.println(teams);

			teamRepository.saveAll(teams);

			System.out.println("------------------------");

			List<IVenueCount> objsVenues = matchRepository
					.countTotalMatchesByVenue();
			List<Venue> venues = objsVenues.stream().map(x -> new VenueBuilder()
					.setName(x.getVenueName()).setCity(x.getCity())
					.setTotalMatches(x.getTotalMatches())
					.setTotalWins(x.getTotalWins())
					.setTotalTies(x.getTotalTies())
					.setTotalNoResult(x.getTotalNoResult())
					.setTotalTossWinBatFirst(x.getTotalTossWinBatFirst())
					.setTotalTossWinFieldFirst(x.getTotalTossWinFieldFirst())
					.setTotalWins(x.getTotalWins())
					.setTotalWinsBatFirst(x.getTotalWinsBatFirst())
					.setTotalWinsFieldFirst(x.getTotalWinsFieldFirst())
					.setTotalWinsByRuns(x.getTotalWinsByRuns())
					.setTotalWinsByWikets(x.getTotalWinsByWikets())
					.setTeams(x.getTeams()).build())
					.collect(Collectors.toList());
			System.out.println(venues);

			venueRepository.saveAll(venues);
		}
	}

}
