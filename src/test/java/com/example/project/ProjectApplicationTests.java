package com.example.project;

import com.example.project.football.calendar.Calendar;
import com.example.project.football.calendar.CalendarRepository;
import com.example.project.football.game.Game;
import com.example.project.football.game.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private CalendarRepository calendarRepository;
	@Autowired
	private GameRepository gameRepository;

	@Test
	void contextLoads() {
		for (int i = 0; i < 10; i++) {
			Calendar calendar = new Calendar();
			calendar.setName("Calendar " + i); // 캘린더 이름 설정
			calendarRepository.save(calendar); // 캘린더 저장

			// 각 캘린더에 대해 10개의 게임 생성 및 저장
			for (int j = 0; j < 10; j++) {
				Game game = new Game();
				game.setTitle("대전 삼정 풋살구장 / 20:00");
				game.setContent("날짜: 2024-05-30\n" +
						"장소: 대전삼정풋살구장\n" +
						"시간: 20:00 ~ 22:00\n" +
						"구장 정보: 33 X 16m\n" +
						"샤워실 / 풋살화 대여 / 무료 주차\n" +
						"구장 특이사항\n" +
						"흡연: 흡연구역 외 절대 금연(흡연구역 외에서 흡연 적발시 이후 서비스 이용에 제제가 될 수 있습니다.)\n" +
						"풋살화 대여: O (사이즈 : 235 ~ 285mm / 대여료 : 3천원, 신분증 지참)");
				game.setCreateDate(LocalDateTime.now());
				game.setCalendar(calendar); // 각 게임에 해당 캘린더 설정
				gameRepository.save(game); // 게임 저장
			}
		}
	}
}