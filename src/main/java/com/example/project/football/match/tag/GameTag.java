package com.example.project.football.match.tag;

import com.example.project.football.match.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.project.football.match.tag.tag.Tag;

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
