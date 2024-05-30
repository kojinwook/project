package com.example.project.football.apply;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
//    public void create(Game game, String loginId,String content,String nickName, String phone){
//        Apply apply = new Apply();
//        apply.setLoginId(loginId);
//        apply.setContent(content);
//        apply.setNickName(nickName);
//        apply.setPhone(phone);
//        apply.setCreateDate(LocalDateTime.now());
//        apply.setGame(game);
//        this.applyRepository.save(apply);
//    }


    public void save(Apply apply){
        applyRepository.save(apply);
    }


}




