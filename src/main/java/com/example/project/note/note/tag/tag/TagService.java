package com.example.project.note.note.tag.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag getTag(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow();
    }

    public Tag create(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }

    public List<Tag> getTagList() {
        return tagRepository.findAll();
    }
}
