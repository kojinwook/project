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
@RequestMapping("/{CalendarId}/Games")
public class GameController {

    private final GameService GameService;
    private final MainService mainService;

    @PostMapping("/write")
    public String write(@PathVariable("CalendarId") Long CalendarId, ParamHandler paramHandler) {

        mainService.addToCalendar(CalendarId);
        return paramHandler.getRedirectUrl("/");
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("CalendarId") Long CalendarId, @PathVariable("id") Long id,
                         ParamHandler paramHandler) {

        MainDataDto mainDataDto = mainService.getMainData(CalendarId, id, paramHandler.getKeyword(), paramHandler.getSort());
        model.addAttribute("mainDataDto", mainDataDto);

        return "main";
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable("calendarId") Long calendarId, @PathVariable("id") Long id, String title, String content, ParamHandler paramHandler){
        Game game = GameService.getGame(id);

        if(title.trim().length() == 0) {
            title = "마감된 매치";
        }

        game.setTitle(title);
        game.setContent(content);

        GameService.save(game);
        return paramHandler.getRedirectUrl("/%d/Games/%d".formatted(calendarId, id));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("calendarId") Long calendarId, @PathVariable("id") Long id, ParamHandler paramHandler) {

        GameService.delete(id);
        return paramHandler.getRedirectUrl("/");
    }


}
