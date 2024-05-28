package com.example.project.football.game.tag.tag;

import com.example.project.football.MainDataDto;
import com.example.project.football.MainService;
import com.example.project.football.ParamHandler;
import com.example.project.football.game.Game;
import com.example.project.football.game.tag.GameTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final MainService mainService;

    @GetMapping("/{tagId}/games")
    public String tagGames(@PathVariable("tagId") Long tagId, Long calendarId, Long gameId, ParamHandler paramHandler, Model model) {
        Tag tag = tagService.getTag(tagId);
        List<GameTag> gameTagList = tag.getGameTagList();
        List<Game> gameListByTag = new ArrayList<>();

        for (GameTag gameTag : gameTagList) {
            gameListByTag.add(gameTag.getGame());
        }

        MainDataDto mainDataDto = mainService.getMainData(calendarId, gameId, paramHandler.getKeyword(), paramHandler.getSort());

        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("gameListByTag", gameListByTag);
        model.addAttribute("targetTag", tag);

        return "main";
    }
}

