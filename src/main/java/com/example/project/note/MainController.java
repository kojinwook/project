package com.example.project.note;


import com.example.project.note.note.Note;
import com.example.project.note.note.NoteRepository;
import com.example.project.note.note.NoteService;
import com.example.project.note.notebook.Notebook;
import com.example.project.note.notebook.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
