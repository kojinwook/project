package com.example.project.note.note;


import com.example.project.note.note.tag.NoteTag;
import com.example.project.note.notebook.Notebook;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notebook notebook;

    @OneToMany(mappedBy = "note")
    private List<NoteTag> noteTagList = new ArrayList<>();
}