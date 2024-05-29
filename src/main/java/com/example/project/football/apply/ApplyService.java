package com.example.project.football.apply;


import com.example.project.football.DataNotFoundException;
import com.example.project.football.game.Game;
import com.example.project.football.member.Member;
import com.example.project.football.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    public void create(Game game,String loginId,String content,String phone){
        Apply apply = new Apply();
        apply.setLoginId(loginId);
        apply.setContent(content);
        apply.setPhone(phone);
        apply.setCreateDate(LocalDateTime.now());
        apply.setGame(game);
        this.applyRepository.save(apply);
    }

//    public Apply getApply(Long id) {
//        Optional<Apply> apply = this.applyRepository.findById(id);
//        if (apply.isPresent()) {
//            return apply.get();
//        } else {
//            throw new DataNotFoundException("apply not found");
//        }
//    }
//    public void delete(Apply apply) {
//        this.applyRepository.delete(apply);
//    }
//    public void modify(Apply apply, String content) {
//        apply.setContent(content);
//        apply.setModifyDate(LocalDateTime.now());
//        this.applyRepository.save(apply);
//    }
    public void save(Apply apply){
        applyRepository.save(apply);
    }



}




