package com.example.project.football;


import com.example.project.football.calendar.Calendar;
import com.example.project.football.game.Game;
import com.example.project.football.game.tag.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MainDataDto {
    List<Calendar> calendarList = new ArrayList<>();
    Calendar targetCalendar;
    List<Game> GameList = new ArrayList<>();
    Game targetGame;
    List<Calendar> searchedCalendarList = new ArrayList<>();
    List<Game> searchedGameList = new ArrayList<>();
    List<Tag> tagList = new ArrayList<>();
}
