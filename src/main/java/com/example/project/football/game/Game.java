package com.example.project.football.game;

import com.example.project.football.apply.Apply;
import com.example.project.football.calendar.Calendar;
import com.example.project.football.game.tag.GameTag;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;
    @OneToMany(mappedBy = "game" , cascade = CascadeType.REMOVE)
    private List<GameTag> gameTagList = new ArrayList<>();

    @OneToMany(mappedBy = "game",cascade = CascadeType.REMOVE)
    private List<Apply> applyList = new ArrayList<>();



}