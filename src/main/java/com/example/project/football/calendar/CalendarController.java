package com.example.project.football.calendar;

import com.example.project.football.MainService;
import com.example.project.football.ParamHandler;
import com.example.project.football.calendar.Calendar;
import com.example.project.football.calendar.CalendarService;
import com.example.project.football.match.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
    private final MainService mainService;

    @PostMapping("/books/write")
    public String write(ParamHandler paramHandler) {
        mainService.saveDefaultCalendar();
        return paramHandler.getRedirectUrl("/");

    }

    @PostMapping("/groups/{calendarId}/books/write")
    public String groupWrite(@PathVariable("calendarId") Long calendarId, ParamHandler paramHandler) {

        mainService.saveGroupCalendar(calendarId);
        return paramHandler.getRedirectUrl("/");
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable("id") Long id, ParamHandler paramHandler) {
        Calendar calendar = calendarService.getCalendar(id);
        Game game = calendar.getGameList().get(0);

        return paramHandler.getRedirectUrl("/books/%d/Games/%d".formatted(id, game.getId()));
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable("id") Long id, ParamHandler paramHandler) {
        calendarService.delete(id);
        return paramHandler.getRedirectUrl("/");
    }

    @PostMapping("/books/{id}/update")
    public String update(@PathVariable("id") Long id, Long targetGameId, String name, ParamHandler paramHandler) {
        calendarService.updateName(id, name);
        return paramHandler.getRedirectUrl("/books/%d/Games/%d".formatted(id, targetGameId));
    }

    @PostMapping("/books/{id}/move")
    public String move(@PathVariable("id") Long id, Long destinationId, Long targetGameId, ParamHandler paramHandler) {
        calendarService.move(id, destinationId);

        return paramHandler.getRedirectUrl("/books/%d/Games/%d".formatted(id, destinationId));
    }
}
