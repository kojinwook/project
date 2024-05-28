package com.example.project.football.calendar;


import com.example.project.football.match.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    private Calendar parent;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.REMOVE)
    List<Game> gameList = new ArrayList<>();

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    List<Calendar> children = new ArrayList<>();

    public void addChild(Calendar child) {
        child.setParent(this);
        children.add(child);
    }

    public void addGame(Game game) {
        game.setCalendar(this);
        gameList.add(game);
    }
}
