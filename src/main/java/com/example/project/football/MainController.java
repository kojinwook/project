package com.example.project.football;


import com.example.project.football.game.GameService;
import com.example.project.football.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;
    private final InquiryService inquiryService;

    @RequestMapping("/")
    public String main(Model model, ParamHandler paramHandler) {

        MainDataDto mainDataDto = mainService.getDefaultMainData(paramHandler.getKeyword());
        model.addAttribute("mainDataDto", mainDataDto);
        Long count = inquiryService.getCount();
        model.addAttribute("count", count);

        return "main";
    }

    @GetMapping("test")
    @ResponseBody
    public String test(String fruits) {
        return fruits;
    }
    @RequestMapping("/inquiry")
    public String inquiry(){
        return "inquiry";
    }

    @GetMapping("/apply/{id}")
    public String apply(@PathVariable("id") Long id, Model model) {
        model.addAttribute("gameId", id);
        return "apply";
    }


}
