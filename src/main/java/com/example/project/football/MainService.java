package com.example.project.football;

import com.example.project.football.match.tag.tag.Tag;
import com.example.project.football.calendar.Calendar;
import com.example.project.football.calendar.CalendarService;
import com.example.project.football.match.Game;
import com.example.project.football.match.GameService;
import com.example.project.football.match.tag.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final CalendarService calendarService;
    private final GameService gameService;
    private final TagService tagService;

    public MainDataDto getDefaultMainData(String keyword) {
        List<Calendar> calendarList = calendarService.getCalendarList();

        if (calendarList.isEmpty()) {
            Calendar calendar = this.saveDefaultCalendar();
            calendarList.add(calendar);
        }

        Calendar targetCalendar = calendarList.get(0);
        List<Game> GameList = targetCalendar.getGameList();
        Game targetGame = GameList.get(0);

        List<Calendar> searchedCalendarList = calendarService.getSearchedCalendarList(keyword);
        List<Game> searchedGameList = gameService.getSearchedGameList(keyword);
        List<Tag> tagList = tagService.getTagList();

        MainDataDto mainDataDto = new MainDataDto(calendarList, targetCalendar, GameList, targetGame, searchedCalendarList, searchedGameList, tagList);
        return mainDataDto;
    }

    public MainDataDto getMainData(Long calendarId, Long GameId, String keyword, String sort) {

        MainDataDto mainDataDto = this.getDefaultMainData(keyword);
        Calendar targetCalendar = this.getCalendar(calendarId);
        Game targetGame = gameService.getGame(GameId);

        mainDataDto.setTargetCalendar(targetCalendar);
        mainDataDto.setTargetGame(targetGame);

        List<Game> sortedGameList;

        if(sort.equals("date")) {
            sortedGameList = gameService.getSortedListByCreateDate(targetCalendar);
        }
        else  {
            sortedGameList = gameService.getSortedListByTitle(targetCalendar);
        }

        mainDataDto.setGameList(sortedGameList);

        return mainDataDto;
    }

    public Calendar getCalendar(Long calendarId) {
        return calendarService.getCalendar(calendarId);
    }

    public List<Calendar> getCalendarList() {
        return calendarService.getCalendarList();
    }

    public Calendar saveDefaultCalendar() {
        Calendar calendar = new Calendar();
        calendar.setName("새 구장");

        Game Game = gameService.saveDefault();
        calendar.addGame(Game);

        return calendarService.save(calendar);
    }

    public void saveGroupCalendar(Long calendarId) {
        Calendar parent = this.getCalendar(calendarId);
        Calendar child = this.saveDefaultCalendar();
        parent.addChild(child);

        calendarService.save(parent);
    }

    public Calendar addToCalendar(Long calendarId) {
        Calendar calendar = this.getCalendar(calendarId);
        Game Game = gameService.saveDefault();
        calendar.addGame(Game);

        return calendarService.save(calendar);
    }

    public void delete(Long id) {

        Calendar calendar = this.getCalendar(id);

        if(calendar.getChildren().isEmpty()) {
            deleteBasic(calendar);
        }
        else {
            deleteGroup(calendar);
        }
    }

    public void deleteGroup(Calendar calendar) {

                List<Calendar> children = calendar.getChildren();
        for (Calendar child : children) {

            deleteBasic(child);
        }

        deleteBasic(calendar);
    }

    public void deleteBasic(Calendar calendar) {

        List<Game> GameList = calendar.getGameList();

        for (Game Game : GameList) {
            gameService.delete(Game.getId());
        }
        calendarService.delete(calendar.getId());
    }
}

