package ru.malik.piano_task.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.malik.piano_task.server.domain.Question;

public interface QuestionService {
    Page<Question> findAll(String intitle, Pageable pageable);
}
