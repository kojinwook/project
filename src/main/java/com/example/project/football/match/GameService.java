package com.example.project.football.match;


import com.example.project.football.calendar.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;


    public Game saveDefault() {
        Game game = new Game();
        game.setTitle("새로운 매치");
        game.setContent("날짜 : \n" +
                " 장소 : \n" +
                " 시간 : \n" +
                " 구장 정보 : \n ");
        game.setCreateDate(LocalDateTime.now());

        return gameRepository.save(game);
    }

    public Game getGame(Long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    public List<Game> getGameListByCalendar(Calendar targetCalendar) {
        return gameRepository.findByCalendar(targetCalendar);
    }

    public void save(Game Game) {
        gameRepository.save(Game);
    }

    public void delete(Long id) {
        gameRepository.deleteById(id);
    }

    public List<Game> getSearchedGameList(String keyword) {
        return gameRepository.findByTitleContaining(keyword);
    }

    public List<Game> getSortedListByCreateDate(Calendar targetCalendar) {
        return gameRepository.findByCalendarOrderByCreateDateDesc(targetCalendar);
    }

    public List<Game> getSortedListByTitle(Calendar targetCalendar) {
        return gameRepository.findByCalendarOrderByTitle(targetCalendar);
    }
}