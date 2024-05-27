package com.example.project.note.note;


import com.example.project.note.notebook.Notebook;
import com.example.project.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NotebookService notebookService;

    public Note saveDefault(){
        Note note = new Note();
        note.setTitle("새로운 매치");
        note.setContent("* 시간 : \n " +
                "* 장소 : \n " +
                "* 모든 레벨 \n " +
                "* 금액 / 시간  : 인당 10000원 / 2시간 \n " +
                "* 인원수 : 10 ~ 18 명 \n " +
                "* 6 vs 6 3파전 \n " +
                "* 풋살화 / 운동화 착용 \n " +
                "* 주의 사항 : \n " +
                "* 구장 정보 : \n " +
                "* 매치 진행 방식 : \n" +
                "* 매치 규칙 : \n" +
                "환불 정책 : \n ");
        note.setCreateDate(LocalDateTime.now());

        return noteRepository.save(note);

    }
    public Note getNote(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    public List<Note> getNoteListByNotebook(Notebook targetNotebook) {
        return noteRepository.findByNotebook(targetNotebook);
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    public Notebook getNotebook(Long notebookId) {
        return notebookService.getNotebook(notebookId);
    }

    public List<Notebook> getNotebookList() {
        return notebookService.getNotebookList();
    }





}
