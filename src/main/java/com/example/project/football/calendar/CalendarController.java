package com.example.project.football.calendar;

import com.example.project.football.MainService;
import com.example.project.football.ParamHandler;
import com.example.project.football.game.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
    private final MainService mainService;

    @PostMapping("matchs/write")
    public String write(ParamHandler paramHandler) {
        mainService.saveDefaultCalendar();
        return paramHandler.getRedirectUrl("/");

    }

    @PostMapping("/groups/{calendarId}/matchs/write")
    public String groupWrite(@PathVariable("calendarId") Long calendarId, ParamHandler paramHandler) {

        mainService.saveGroupCalendar(calendarId);
        return paramHandler.getRedirectUrl("/");
    }

    @GetMapping("/matchs/{id}")
    public String detail(@PathVariable("id") Long id, ParamHandler paramHandler) {
        Calendar calendar = calendarService.getCalendar(id);
        Game game = calendar.getGameList().get(0);

        return paramHandler.getRedirectUrl("/matchs/%d/games/%d".formatted(id, game.getId()));
    }

    @PostMapping("/matchs/{id}/delete")
    public String delete(@PathVariable("id") Long id, ParamHandler paramHandler) {
        calendarService.delete(id);
        return paramHandler.getRedirectUrl("/");
    }

    @PostMapping("/matchs/{id}/update")
    public String update(@PathVariable("id") Long id, Long targetGameId, String name, ParamHandler paramHandler) {
        calendarService.updateName(id, name);
        return paramHandler.getRedirectUrl("/matchs/%d/games/%d".formatted(id, targetGameId));
    }

    @PostMapping("/matchs/{id}/move")
    public String move(@PathVariable("id") Long id, Long destinationId, Long targetGameId, ParamHandler paramHandler) {
        calendarService.move(id, destinationId);

        return paramHandler.getRedirectUrl("/matchs/%d/games/%d".formatted(id, destinationId));
    }
    @GetMapping("/{calendarId}/games")
    public ResponseEntity<List<Game>> getGamesByCalendarId(@PathVariable("calendarId") Long calendarId) {
        Calendar calendar = calendarService.getCalendarById(calendarId);
        if (calendar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Game> games = calendar.getGameList();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
