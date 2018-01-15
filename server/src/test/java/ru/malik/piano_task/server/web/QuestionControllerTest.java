package ru.malik.piano_task.server.web;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.malik.piano_task.server.domain.Question;
import ru.malik.piano_task.server.service.QuestionService;
import ru.malik.piano_task.server.stack_exchange.OwnerDto;
import ru.malik.piano_task.server.stack_exchange.PageDto;
import ru.malik.piano_task.server.stack_exchange.QuestionDto;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(QuestionController.class)
@EnableSpringDataWebSupport
public class QuestionControllerTest {

    @MockBean
    private QuestionService questionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void find() throws Exception {
        final String intitle = "intitle_test";
        final Pageable pageable = new PageRequest(0, 2);

        final Page<Question> page = new PageImpl<>(Arrays.asList(
                new Question("t1", new Timestamp(123), "o1", "l1", true),
                new Question("t2", new Timestamp(456), "o2", "l2", false)
        ), pageable, 30);


        when(questionService.findAll(intitle, pageable)).thenReturn(page);

        mockMvc.perform(get("/api/1/questions/search?intitle={intitle}&page={page}&size={size}", intitle,0,2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].title", is("t1")))
                .andExpect(jsonPath("$.content[1].title", is("t2")))
                .andExpect(jsonPath("$.content[0].creator", is("o1")))
                .andExpect(jsonPath("$.content[1].creator", is("o2")))
                .andExpect(jsonPath("$.content[0].creationDate", is(123)))
                .andExpect(jsonPath("$.content[1].creationDate", is(456)))
                .andExpect(jsonPath("$.content[0].url", is("l1")))
                .andExpect(jsonPath("$.content[1].url", is("l2")))
                .andExpect(jsonPath("$.content[0].answered", is(true)))
                .andExpect(jsonPath("$.content[1].answered", is(false)))
                .andExpect(jsonPath("$.totalElements", is(30)))
        ;
    }
}
