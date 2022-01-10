package com.example.ipldashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ipldashboard.model.ICity;
import com.example.ipldashboard.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
	Venue getByName(String name);

	List<Venue> getByCity(String city);
	@Query("SELECT city as city, name as venueName FROM Venue where city != 'NA' order by city asc,name asc")
	List<ICity> findCity();
}
