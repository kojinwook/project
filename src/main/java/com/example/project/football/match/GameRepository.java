package com.example.project.football.match;


import com.example.project.football.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByCalendar(Calendar calendar);

    List<Game> findByTitleContaining(String keyword);

    List<Game> findByCalendarOrderByCreateDateDesc(Calendar targetCalendar);

    List<Game> findByCalendarOrderByTitle(Calendar targetCalendar);
}
