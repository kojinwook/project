package com.example.project.football.calendar;


import com.example.project.football.game.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private LocalDate date;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.REMOVE)
    List<Game> gameList = new ArrayList<>();


    public void addGame(Game game) {
        game.setCalendar(this);
        gameList.add(game);
    }
    public void removeGame(Game game){
        game.setCalendar(null);
        gameList.remove(game);
    }
    @ManyToOne
    private Calendar parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    List<Calendar> children = new ArrayList<>();

    public void addChild(Calendar child) {
        child.setParent(this);
        children.add(child);
    }
}
