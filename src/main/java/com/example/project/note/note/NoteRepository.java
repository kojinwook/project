package com.example.project.note.note;


import com.example.project.note.notebook.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
List<Note> findByNotebook(Notebook notebook);
}
