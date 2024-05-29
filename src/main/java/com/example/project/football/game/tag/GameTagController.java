package com.example.project.football.game.tag;

import com.example.project.football.ParamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/games/{gameId}/tags")
public class GameTagController {
    private final GameTagService gameTagService;

    @PostMapping("/create")
    public String create(@PathVariable("gameId") Long gameId, String name, ParamHandler paramHandler) {
        GameTag gameTag = gameTagService.create(gameId, name);
        Long calendarId = gameTag.getGame().getCalendar().getId();
        return paramHandler.getRedirectUrl("/matchs/%d/games/%d".formatted(calendarId, gameId));
    }

    @PostMapping("/{gameTagId}/delete")
    public String delete(@PathVariable("gameId") Long gameId, @PathVariable("gameTagId") Long gameTagId, ParamHandler paramHandler) {
        GameTag gameTag = gameTagService.getGameTag(gameTagId);
        Long calendarId = gameTag.getGame().getCalendar().getId();
        gameTagService.delete(gameTagId);

        return paramHandler.getRedirectUrl("/matchs/%d/games/%d".formatted(calendarId, gameId));
    }
}