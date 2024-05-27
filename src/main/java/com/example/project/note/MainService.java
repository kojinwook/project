package com.example.project.note;

import com.example.project.note.note.Note;
import com.example.project.note.note.NoteService;
import com.example.project.note.note.tag.tag.Tag;
import com.example.project.note.note.tag.tag.TagService;
import com.example.project.note.notebook.Notebook;
import com.example.project.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final NotebookService notebookService;
    private final NoteService noteService;
    private final TagService tagService;

    public MainDataDto getDefaultMainData(String keyword) {
        List<Notebook> notebookList = notebookService.getTopNotebookList();

        if (notebookList.isEmpty()) {
            Notebook notebook = this.saveDefaultNotebook();
            notebookList.add(notebook);
        }

        Notebook targetNotebook = notebookList.get(0);
        List<Note> noteList = targetNotebook.getNoteList();
        Note targetNote = noteList.get(0);

        List<Notebook> searchedNotebookList = notebookService.getSearchedNotebookList(keyword);
        List<Note> searchedNoteList = noteService.getSearchedNoteList(keyword);
        List<Tag> tagList = tagService.getTagList();

        MainDataDto mainDataDto = new MainDataDto(notebookList, targetNotebook, noteList, targetNote, searchedNotebookList, searchedNoteList, tagList);
        return mainDataDto;
    }

    public MainDataDto getMainData(Long notebookId, Long noteId, String keyword, String sort) {

        MainDataDto mainDataDto = this.getDefaultMainData(keyword);
        Notebook targetNotebook = this.getNotebook(notebookId);
        Note targetNote = noteService.getNote(noteId);

        mainDataDto.setTargetNotebook(targetNotebook);
        mainDataDto.setTargetNote(targetNote);

        List<Note> sortedNoteList;

        if(sort.equals("date")) {
            sortedNoteList = noteService.getSortedListByCreateDate(targetNotebook);
        }
        else  {
            sortedNoteList = noteService.getSortedListByTitle(targetNotebook);
        }

        mainDataDto.setNoteList(sortedNoteList);

        return mainDataDto;
    }

    public Notebook getNotebook(Long notebookId) {
        return notebookService.getNotebook(notebookId);
    }

    public List<Notebook> getNotebookList() {
        return notebookService.getNotebookList();
    }

    public Notebook saveDefaultNotebook() {
        Notebook notebook = new Notebook();
        notebook.setName("새 구장");

        Note note = noteService.saveDefault();
        notebook.addNote(note);

        return notebookService.save(notebook);
    }

    public void saveGroupNotebook(Long notebookId) {
        Notebook parent = this.getNotebook(notebookId);
        Notebook child = this.saveDefaultNotebook();
        parent.addChild(child);

        notebookService.save(parent);
    }

    public Notebook addToNotebook(Long notebookId) {
        Notebook notebook = this.getNotebook(notebookId);
        Note note = noteService.saveDefault();
        notebook.addNote(note);

        return notebookService.save(notebook);
    }

    public void delete(Long id) {

        Notebook notebook = this.getNotebook(id);

        if(notebook.getChildren().isEmpty()) {
            deleteBasic(notebook);
        }
        else {
            deleteGroup(notebook);
        }
    }

    public void deleteGroup(Notebook notebook) {

                List<Notebook> children = notebook.getChildren();
        for (Notebook child : children) {

            deleteBasic(child);
        }

        deleteBasic(notebook);
    }

    public void deleteBasic(Notebook notebook) {

        List<Note> noteList = notebook.getNoteList();

        for (Note note : noteList) {
            noteService.delete(note.getId());
        }
        notebookService.delete(notebook.getId());
    }
}

