package com.example.project.football.game.tag;

import com.example.project.football.game.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.project.football.game.tag.tag.Tag;

@Entity
@Getter
@Setter
public class GameTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Game game;
}
