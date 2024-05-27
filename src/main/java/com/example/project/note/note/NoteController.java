package com.example.project.note.note;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/{notebookId}/notes")
public class NoteController {
    private final NoteRepository noteRepository;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/")
    public String main(Model model) {
    List<Note> noteList = noteRepository.findAll();
    if(noteList.isEmpty()){
        saveDefault();
        return "redirect:/";
    }



    model.addAttribute("noteList", noteList);
    model.addAttribute("targetNote", noteList.get(0));

        return "main";
    }

    @PostMapping("/write")
    public String write() {
        saveDefault();
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        Note note = noteRepository.findById(id).get();
        model.addAttribute("targetNote", note);
        model.addAttribute("noteList", noteRepository.findAll());

        return "main";
    }
    @PostMapping("/update")
    public String update(Long id, String title, String content) {
        Note note = noteRepository.findById(id).get();
        note.setTitle(title);
        note.setContent(content);

        noteRepository.save(note);
        return "redirect:/detail/" + id;
    }
    private Note saveDefault(){
        Note note = new Note();
        note.setTitle("new title..");
        note.setContent("");
        note.setCreateDate(LocalDateTime.now());

        noteRepository.save(note);
        return noteRepository.save(note);
    }
}