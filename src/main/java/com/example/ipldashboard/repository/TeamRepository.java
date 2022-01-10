package com.example.ipldashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ipldashboard.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	Team getByName(String name);
	public List<Team> findAllByOrderByNameAsc();

}
