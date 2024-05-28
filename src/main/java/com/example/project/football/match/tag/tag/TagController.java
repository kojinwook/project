package com.example.project.football.match.tag.tag;

import com.example.project.football.MainDataDto;
import com.example.project.football.MainService;
import com.example.project.football.ParamHandler;
import com.example.project.football.match.Game;
import com.example.project.football.match.tag.GameTag;
import com.example.project.football.match.tag.tag.TagService;
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

    @GetMapping("/{tagId}/Games")
    public String tagGames(@PathVariable("tagId") Long tagId, Long CalendarId, Long gameId, ParamHandler paramHandler, Model model) {
        Tag tag = tagService.getTag(tagId);
        List<GameTag> GameTagList = tag.getGameTagList();
        List<Game> GameListByTag = new ArrayList<>();

        for (GameTag gameTag : GameTagList) {
            GameListByTag.add(gameTag.getGame());
        }

        MainDataDto mainDataDto = mainService.getMainData(CalendarId, gameId, paramHandler.getKeyword(), paramHandler.getSort());

        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("GameListByTag", GameListByTag);
        model.addAttribute("targetTag", tag);

        return "main";
    }
}

