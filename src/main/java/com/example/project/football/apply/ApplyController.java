package com.example.project.football.apply;


import com.example.project.football.game.Game;
import com.example.project.football.game.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {
    private final ApplyService applyService;
    private final GameService gameService;

    @GetMapping("/application")
    public String showApplyForm(Model model){
        model.addAttribute("apply", new Apply());
        return "apply";
    }


    @PostMapping("/application")
    public String submitApply(@Valid Apply apply,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "apply";
        }
        applyService.save(apply);
        return "apply_success";
    }
//    @PostMapping("/apply/create/{id}")
//    public String createApply(Model model, @PathVariable("id") Long id,
//                              @RequestParam(value="content")String content){
//        Game game = this.gameService.getGame(id);
//        this.applyService.create(game,content);
//        return "redirect:/";
//    }
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/apply/delete/{id}")
//    public String applyDelete(Principal principal, @PathVariable("id") Long id) {
//        Apply apply = this.applyService.getApply(id);
//        this.applyService.delete(apply);
//        return "redirect:/";
//    }
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/apply/modify/{id}")
//    public String answerModify(ApplyForm applyForm, @PathVariable("id") Long id, Principal principal) {
//        Apply apply = this.applyService.getApply(id);
//        applyForm.setContent(apply.getContent());
//        return "modify";
//    }
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/apply/modify/{id}")
//    public String answerModify(@Valid ApplyForm applyForm, BindingResult bindingResult,
//                               @PathVariable("id") Long id, Principal principal) {
//        if (bindingResult.hasErrors()) {
//            return "redirect:/";
//        }
//        Apply apply = this.applyService.getApply(id);
////        if (!apply.getAuthor().getLoginId().equals(principal.getName())) {
////            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
////        }
//        this.applyService.modify(apply, applyForm.getContent());
//        return "redirect:/";
//    }






}
