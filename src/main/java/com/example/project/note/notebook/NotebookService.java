package com.example.project.note.notebook;

import com.example.project.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;
    private final NoteService noteService;
    public Notebook getNotebook(Long notebookId) {
        return notebookRepository.findById(notebookId).orElseThrow();
    }

    public List<Notebook> getNotebookList() {
        return notebookRepository.findAll();
    }

    public Notebook save(Notebook notebook){
        return notebookRepository.save(notebook);
    }
    public void delete(Long id){
        notebookRepository.deleteById(id);
    }

}
