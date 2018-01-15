package ru.malik.piano_task.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.malik.piano_task.server.domain.Question;
import ru.malik.piano_task.server.service.QuestionService;
import ru.malik.piano_task.shared.QuestionDto;

@RestController
@RequestMapping(path = "/api/1/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    Page<QuestionDto> find(@RequestParam(value = "intitle") String intitle, Pageable pageable) {
        final Page<Question> page = questionService.findAll(intitle, pageable);
        return page.map(q -> QuestionDto.builder()
                .creationDate(q.getDate())
                .url(q.getUrl())
                .creator(q.getOwner())
                .title(q.getTitle())
                .answered(q.isAnswered())
                .build()
        );
    }
}
