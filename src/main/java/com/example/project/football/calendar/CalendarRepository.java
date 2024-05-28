package com.example.project.football.calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long>{


    List<Calendar> findByNameContaining(String keyword);
}
