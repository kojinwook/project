package com.example.project.football.game;

import com.example.project.football.MainDataDto;
import com.example.project.football.MainService;
import com.example.project.football.ParamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/matchs/{calendarId}/games")
public class GameController {

    private final GameService gameService;
    private final MainService mainService;

    @PostMapping("/write")
    public String write(@PathVariable("calendarId") Long calendarId, ParamHandler paramHandler) {

        mainService.addToCalendar(calendarId);
        return paramHandler.getRedirectUrl("/");
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("calendarId") Long calendarId, @PathVariable("id") Long id,
                         ParamHandler paramHandler) {

        MainDataDto mainDataDto = mainService.getMainData(calendarId, id, paramHandler.getKeyword(), paramHandler.getSort());
        model.addAttribute("mainDataDto", mainDataDto);

        return "main";
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable("calendarId") Long calendarId, @PathVariable("id") Long id, String title, String content, ParamHandler paramHandler){
        Game game = gameService.getGame(id);

        if(title.trim().length() == 0) {
            title = "마감된 매치";
        }

        game.setTitle(title);
        game.setContent(content);

        gameService.save(game);
        return paramHandler.getRedirectUrl("/matchs/%d/games/%d".formatted(calendarId, id));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("calendarId") Long calendarId, @PathVariable("id") Long id, ParamHandler paramHandler) {

        gameService.delete(id);
        return paramHandler.getRedirectUrl("/");
    }


}
