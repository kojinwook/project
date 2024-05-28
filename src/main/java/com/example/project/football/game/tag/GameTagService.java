package com.example.project.football.game.tag;

import com.example.project.football.game.Game;
import com.example.project.football.game.GameService;
import com.example.project.football.game.tag.tag.Tag;
import com.example.project.football.game.tag.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameTagService {
    private final GameTagRepository gameTagRepository;
    private final GameService gameService;
    private final TagService tagService;

    public GameTag getGameTag(Long GameTagId) {
        return gameTagRepository.findById(GameTagId).orElseThrow();
    }

    public GameTag create(Long GameId, String name) {
        Game Game = gameService.getGame(GameId);
        Tag tag = tagService.create(name);

        GameTag gameTag = new GameTag();
        gameTag.setGame(Game);
        gameTag.setTag(tag);

        return gameTagRepository.save(gameTag);
    }

    public void delete(Long GameTagId) {
        gameTagRepository.deleteById(GameTagId);
    }
}

