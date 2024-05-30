package com.example.project.football.game;


import com.example.project.football.calendar.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;


    public Game saveDefault() {
        Game game = new Game();
        game.setTitle("새로운 매치");
        game.setContent(" 날짜 : 2024-05-30 \n" +
                " 장소 : 대전삼정풋살구장 \n" +
                " 시간 : 20 : 00 ~ 22 : 00\n" +
                " 구장 정보 : 33 X 16m \n" +
                " 샤워실 / 풋살화 대여 / 무료 주차 \n" +
                " 구장 특이사항 \n" +
                " 흡연 : 흡연구역 외 절대 금연(흡연구역 외에서 흡연 적발시 이후 서비스 이용에 제제가 될 수 있습니다.) \n" +
                " 풋살화 대여 : O (사이즈 : 235 ~ 285mm / 대여료 : 3천원, 신분증 지참)");
        game.setCreateDate(LocalDateTime.now());

        return gameRepository.save(game);
    }

    public Game getGame(Long id) {
        return gameRepository.findById(id).orElseThrow();
    }



    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }
    public Game save(Game Game) {
        return gameRepository.save(Game);
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