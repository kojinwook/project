package com.example.project.football.apply;

import com.example.project.football.game.Game;
import com.example.project.football.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {


}
