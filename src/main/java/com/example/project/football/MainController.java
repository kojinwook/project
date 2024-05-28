package com.example.project.football;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @RequestMapping("/")
    public String main(Model model, ParamHandler paramHandler) {

        MainDataDto mainDataDto = mainService.getDefaultMainData(paramHandler.getKeyword());
        model.addAttribute("mainDataDto", mainDataDto);
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



}
