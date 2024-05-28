package com.example.project.football.game.tag;

import com.example.project.football.ParamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/Games/{GameId}/tags")
public class GameTagController {
    private final GameTagService gameTagService;

    @PostMapping("/create")
    public String create(@PathVariable("GameId") Long GameId, String name, ParamHandler paramHandler) {
        GameTag GameTag = gameTagService.create(GameId, name);
        Long calendarId = GameTag.getGame().getCalendar().getId();
        return paramHandler.getRedirectUrl("/books/%d/Games/%d".formatted(calendarId, GameId));
    }

    @PostMapping("{GameTagId}/delete")
    public String delete(@PathVariable("GameId") Long GameId, @PathVariable("GameTagId") Long GameTagId, ParamHandler paramHandler) {
        GameTag gameTag = gameTagService.getGameTag(GameTagId);
        Long calendarId = gameTag.getGame().getCalendar().getId();
        gameTagService.delete(GameTagId);

        return paramHandler.getRedirectUrl("/books/%d/Games/%d".formatted(calendarId, GameId));
    }
}