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
        if(title.trim().length()==0){
            title="마감된 매치";
        }
        note.setTitle(title);
        note.setContent(content);

        noteRepository.save(note);
        return "redirect:/detail/" + id;
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        noteRepository.deleteById(id);
        return "redirect:/";
    }
    private Note saveDefault(){
        Note note = new Note();
        note.setTitle("새 매치");
        note.setContent("* 시간 : \n " +
                "* 장소 : \n +" +
                "* 모든 레벨 \n " +
                "* 금액 / 시간  : 인당 10000원 / 2시간 \n" +
                "* 인원수 : 10 ~ 18 명 \n" +
                "* 6 vs 6 3파전 \n" +
                "* 풋살화 / 운동화 착용 \n" +
                "* 주의 사항 :  \n" +
                "* 구장 정보 : \n" +
                "* 매치 진행 방식 : \n" +
                "* 매치 규칙 : \n" +
                "환불 정책 : \n);");
        note.setCreateDate(LocalDateTime.now());

        noteRepository.save(note);
        return noteRepository.save(note);
    }
}