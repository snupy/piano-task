package ru.malik.piano_task.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.malik.piano_task.server.domain.Question;
import ru.malik.piano_task.server.stack_exchange.PageDto;
import ru.malik.piano_task.server.stack_exchange.QuestionDto;
import ru.malik.piano_task.server.stack_exchange.StackExchangeClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private StackExchangeClient stackExchangeClient;

    public Page<Question> findAll(String intitle, Pageable pageable) {
        final PageDto<QuestionDto> pageDto = stackExchangeClient.searchInStackOverflow(pageable, intitle);
        final List<Question> questions = pageDto.getItems().stream()
                .map(o -> Question.builder()
                        .date(o.getCreationDate())
                        .owner(o.getOwner().getDisplayName())
                        .title(o.getTitle())
                        .url(o.getLink())
                        .answered(o.isAnswered())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(questions, pageable, pageDto.getTotal());
    }
}
