package ru.malik.piano_task.server.stack_exchange;

import org.springframework.data.domain.Pageable;

public interface StackExchangeClient {
    PageDto<QuestionDto> search(Pageable pageable, String intitle, String site);

    default PageDto<QuestionDto> searchInStackOverflow(Pageable pageable, String intitle) {
        return search(pageable, intitle, "stackoverflow");
    }
}
