package com.example.project.note.note;


import com.example.project.note.notebook.Notebook;
import com.example.project.note.notebook.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/{notebookId}/notes")
public class NoteController {
    private final NoteRepository noteRepository;
    private final NotebookRepository notebookRepository;
    private final NoteService noteService;
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }



    @PostMapping("/write")
    public String write(@PathVariable("notebookId") Long notebookId) {
        Notebook notebook = notebookRepository.findById(notebookId).orElseThrow();
        noteService.saveDefault(notebook);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("id") Long id,
                         @PathVariable("notebookId") Long notebookId) {
        Note note = noteRepository.findById(id).get();
        List<Notebook> notebookList = notebookRepository.findAll();
        Notebook targetNotebook = notebookRepository.findById(notebookId).get();
        List<Note> noteList = noteRepository.findByNotebook(targetNotebook);
        model.addAttribute("notebookList",notebookList);
        model.addAttribute("targetNotebook",targetNotebook);
        model.addAttribute("targetNote", note);
        model.addAttribute("noteList", noteList);

        return "main";
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable("notebookId")Long notebookId,
                         @PathVariable("id") Long id, String title, String content) {
        Note note = noteRepository.findById(id).get();
        if(title.trim().length()==0){
            title="마감된 매치";
        }
        note.setTitle(title);
        note.setContent(content);

        noteRepository.save(note);
        return "redirect:/books/%d/notes/%d".formatted(notebookId,id);
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id,@PathVariable("notebookId") Long notebookId){
        noteRepository.deleteById(id);
        return "redirect:/";
    }

}