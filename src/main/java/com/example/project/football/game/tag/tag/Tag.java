package com.example.project.football.game.tag.tag;


import com.example.project.football.game.tag.GameTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<GameTag> GameTagList = new ArrayList<>();


}
