package com.example.project.note;

import com.example.project.note.note.Note;
import com.example.project.note.note.tag.tag.Tag;
import com.example.project.note.notebook.Notebook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MainDataDto {
    List<Notebook> notebookList = new ArrayList<>();
    Notebook targetNotebook;
    List<Note> noteList = new ArrayList<>();
    Note targetNote;
    List<Notebook> searchedNotebookList = new ArrayList<>();
    List<Note> searchedNoteList = new ArrayList<>();
    List<Tag> tagList = new ArrayList<>();
}
