package ru.malik.piano_task.server.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.malik.piano_task.server.domain.Question;
import ru.malik.piano_task.server.stack_exchange.OwnerDto;
import ru.malik.piano_task.server.stack_exchange.PageDto;
import ru.malik.piano_task.server.stack_exchange.QuestionDto;
import ru.malik.piano_task.server.stack_exchange.StackExchangeClient;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class QuestionServiceImplTest {

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Mock
    private StackExchangeClient stackExchangeClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        final String intitle = "intitle_test";
        final Pageable pageable = new PageRequest(1, 11);
        final PageDto<QuestionDto> pageDto = new PageDto<>(Arrays.asList(
                new QuestionDto("t1",
                        new OwnerDto("o1"),
                        new Timestamp(123), "l1", true),
                new QuestionDto("t2",
                        new OwnerDto("o2"),
                        new Timestamp(456), "l2", false)
        ), 30);

        final Page<Question> exp = new PageImpl<>(Arrays.asList(
                new Question("t1", new Timestamp(123), "o1", "l1", true),
                new Question("t2", new Timestamp(456), "o2", "l2", false)
        ), pageable, 30);


        when(stackExchangeClient.searchInStackOverflow(pageable, intitle))
                .thenReturn(pageDto);

        final Page<Question> res = questionService.findAll(intitle, pageable);

        assertEquals(exp, res);
    }
}