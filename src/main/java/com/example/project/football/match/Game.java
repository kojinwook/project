package com.example.project.football.match;

import com.example.project.football.calendar.Calendar;
import com.example.project.football.match.tag.GameTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private String notice;
    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;

    @OneToMany(mappedBy = "game")
    private List<GameTag> GameTagList = new ArrayList<>();
}