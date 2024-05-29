package com.example.project.football.apply;


import com.example.project.football.game.Game;
import com.example.project.football.game.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {
    private final ApplyService applyService;
    private final GameService gameService;

    @PostMapping("/create/{id}")
    public String createApply(Model model, @PathVariable("id") Long id,
                              @RequestParam(value="content")String content){
        Game game = this.gameService.getGame(id);
        this.applyService.create(game,content);
        return "redirect:/";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete/{id}")
    public String applyDelete(Principal principal, @PathVariable("id") Long id) {
        Apply apply = this.applyService.getApply(id);
        if (!apply.getAuthor().getLoginId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.applyService.delete(apply);
        return "redirect:/";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(ApplyForm applyForm, @PathVariable("id") Long id, Principal principal) {
        Apply apply = this.applyService.getApply(id);
        if (!apply.getAuthor().getLoginId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        applyForm.setContent(apply.getContent());
        return "redirect:/";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid ApplyForm applyForm, BindingResult bindingResult,
                               @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        Apply apply = this.applyService.getApply(id);
        if (!apply.getAuthor().getLoginId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.applyService.modify(apply, applyForm.getContent());
        return "redirect:/";
    }






}
