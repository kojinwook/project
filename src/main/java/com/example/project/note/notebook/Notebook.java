package com.example.project.note.notebook;

import com.example.project.note.note.Note;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "notebook")
    List<Note> noteList = new ArrayList<>();

    @ManyToOne
    private Notebook parent;

    @OneToMany(mappedBy = "parent")
    List<Notebook> children = new ArrayList<>();

    public void addChild(Notebook child){
        child.setParent(this);
        children.add(child);
    }
    public void addNote(Note note){
        note.setNotebook(this);
        noteList.add(note);
    }

}
