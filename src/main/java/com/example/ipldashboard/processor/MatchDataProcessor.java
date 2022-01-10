package com.example.ipldashboard.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.model.MatchInput;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	private DateTimeFormatter dateFormat = DateTimeFormatter
			.ofPattern("M/d/yyyy");
	@Override
	public Match process(MatchInput matchInput) throws Exception {
		Match match = new Match();
		try {
			System.out.println("matchInput: " + matchInput.getDate());
			match.setId(Long.parseLong(matchInput.getId()));
			match.setCity(matchInput.getCity());
			match.setDate(LocalDate.parse(matchInput.getDate(), dateFormat));
			match.setPlayerOfMatch(matchInput.getPlayer_of_match());
			match.setResult(matchInput.getResult());
			match.setResultMargin(matchInput.getResult_margin());
			match.setUmpire1(matchInput.getUmpire1());
			match.setUmpire2(matchInput.getUmpire2());
			match.setVenue(matchInput.getVenue());
			match.setWinner(matchInput.getWinner());
			if (matchInput.getVenue()
					.equalsIgnoreCase("Dubai International Cricket Stadium")) {
				match.setCity("Dubai");
			}
			// Sharjah Cricket Stadium
			if (matchInput.getVenue()
					.equalsIgnoreCase("Sharjah Cricket Stadium")) {
				match.setCity("Sharjah");
			}
			if (matchInput.getVenue()
					.equalsIgnoreCase("M Chinnaswamy Stadium")) {
				match.setVenue("M.Chinnaswamy Stadium");
				match.setCity("Bengaluru");
			}

			String firstInningsTeam = matchInput.getToss_winner()
					.equals(matchInput.getTeam1())
							? matchInput.getTeam1()
							: matchInput.getTeam2();
			String secondInningsTeam = matchInput.getToss_winner()
					.equals(matchInput.getTeam2())
							? matchInput.getTeam1()
							: matchInput.getTeam2();
			match.setTeam1(matchInput.getTeam1());
			match.setTeam2(matchInput.getTeam2());
			// if (matchInput.getWinner().equalsIgnoreCase("winner")) {
			// match.setResult(ResultEnum.WINNER);
			// } else if (matchInput.getResult().equalsIgnoreCase("winner")) {
			// match.setResult(ResultEnum.WINNER);
			// } else if (matchInput.getResult().equalsIgnoreCase("winner")) {
			// match.setResult(ResultEnum.WINNER);
			// }
			match.setTossDecision(matchInput.getToss_decision());
			match.setTossWinner(matchInput.getToss_winner());
			System.out.println(match);

			return match;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
