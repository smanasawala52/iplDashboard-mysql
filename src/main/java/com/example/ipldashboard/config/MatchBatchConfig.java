package com.example.ipldashboard.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.model.MatchInput;
import com.example.ipldashboard.processor.MatchDataProcessor;

@Configuration
@EnableBatchProcessing
public class MatchBatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<MatchInput> reader() {
		return new FlatFileItemReaderBuilder<MatchInput>()
				.name("matchInputItemReader")
				.resource(new ClassPathResource("match-data.csv")).delimited()
				.names(new String[]{"id", "city", "date", "player_of_match",
						"venue", "neutral_venue", "team1", "team2",
						"toss_winner", "toss_decision", "winner", "result",
						"result_margin", "eliminator", "method", "umpire1",
						"umpire2"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {
					{
						setTargetType(MatchInput.class);
					}
				}).build();

	}
	@Bean
	public MatchDataProcessor processor() {
		return new MatchDataProcessor();
	}
	@Bean
	public JdbcBatchItemWriter<Match> writer(javax.sql.DataSource datasource) {
		return new JdbcBatchItemWriterBuilder<Match>()
				.itemSqlParameterSourceProvider(
						new BeanPropertyItemSqlParameterSourceProvider<Match>())
				.sql("insert into matches(id, city, date, player_of_match, venue, team1, team2, winner, result, result_margin, umpire1, umpire2,toss_winner,toss_decision) "
						+ "values ( :id, :city, :date, :playerOfMatch, :venue, :team1, :team2, :winner, :result, :resultMargin, :umpire1, :umpire2,:tossWinner,:tossDecision)")
				.dataSource(datasource).build();
	}

	@Bean
	public Job importMatchData(JobCompletionNotificationListener listener,
			Step step1) {
		return jobBuilderFactory.get("importMatchData")
				.incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1).end().build();

	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Match> writer) {
		return stepBuilderFactory.get("step1").<MatchInput, Match>chunk(10)
				.reader(reader()).processor(processor()).writer(writer).build();
	}
}
