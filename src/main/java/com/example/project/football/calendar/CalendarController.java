package com.example.project.football.calendar;

import com.example.project.football.MainService;
import com.example.project.football.ParamHandler;
import com.example.project.football.game.Game;
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

    @PostMapping("/write")
    public String write(ParamHandler paramHandler) {
        mainService.saveDefaultCalendar();
        return paramHandler.getRedirectUrl("/");

    }

    @PostMapping("/groups/{calendarId}/write")
    public String groupWrite(@PathVariable("calendarId") Long calendarId, ParamHandler paramHandler) {

        mainService.saveGroupCalendar(calendarId);
        return paramHandler.getRedirectUrl("/");
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, ParamHandler paramHandler) {
        Calendar calendar = calendarService.getCalendar(id);
        Game game = calendar.getGameList().get(0);

        return paramHandler.getRedirectUrl("/%d/Games/%d".formatted(id, game.getId()));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, ParamHandler paramHandler) {
        calendarService.delete(id);
        return paramHandler.getRedirectUrl("/");
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, Long targetGameId, String name, ParamHandler paramHandler) {
        calendarService.updateName(id, name);
        return paramHandler.getRedirectUrl("/%d/Games/%d".formatted(id, targetGameId));
    }

    @PostMapping("/{id}/move")
    public String move(@PathVariable("id") Long id, Long destinationId, Long targetGameId, ParamHandler paramHandler) {
        calendarService.move(id, destinationId);

        return paramHandler.getRedirectUrl("/%d/Games/%d".formatted(id, destinationId));
    }
}
