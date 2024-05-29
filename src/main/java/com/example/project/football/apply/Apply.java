package com.example.project.football.apply;


import com.example.project.football.game.Game;
import com.example.project.football.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String nickname;
    private LocalDateTime createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    @ManyToOne
    private Member author;
    private LocalDateTime modifyDate;





}
