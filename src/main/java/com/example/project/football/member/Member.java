package com.example.project.football.member;

import com.example.project.football.apply.Apply;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String loginId;
    private String password;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    private LocalDateTime createDate;



}
