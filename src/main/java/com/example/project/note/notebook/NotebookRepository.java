package com.example.project.note.notebook;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook , Long> {
}
