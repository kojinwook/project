package com.example.project.football.apply;


import com.example.project.football.game.Game;
import com.example.project.football.game.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class ApplyController {
    private final ApplyService applyService;
    private final GameService gameService;

    @GetMapping("/application/{gameId}")
    public String showApplyForm(Model model, @PathVariable("gameId") Long gameId){
        model.addAttribute("apply", new Apply());
        return "apply";
    }


    @PostMapping("/application/{gameId}")
    public String submitApply(@Valid Apply apply, @PathVariable("gameId") Long gameId,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "apply";
        }
        applyService.save(apply,gameId);
        return "apply_success";
    }

//    @GetMapping("/application/finish")
//    public String applicationFinish(){
//        return "applicationFinish";
//    }












}
