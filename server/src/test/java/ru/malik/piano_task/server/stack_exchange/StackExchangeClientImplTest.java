package ru.malik.piano_task.server.stack_exchange;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.when;
import static ru.malik.piano_task.server.stack_exchange.StackExchangeClientImpl.TR_QUESTIONS_PAGE;

public class StackExchangeClientImplTest {

    @InjectMocks
    private StackExchangeClientImpl cl;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void search() {
        final Pageable pageable = new PageRequest(1, 11);
        final String intitle = "java";
        final String site = "stackoverflow";
        final PageDto<QuestionDto> page = new PageDto<>();

        final URI uri = UriComponentsBuilder
                .fromHttpUrl("http://api.stackexchange.com/2.2/search?page=2&pagesize=11&intitle=java&site=stackoverflow&filter=!*IXk1kQqzsc8kyMHSIZp7Ywi-mODTKQTFm18PsvR4OBIEB(vKZIPfYsKDDqkIW")
                .build().toUri();

        ArgumentCaptor<URI> uriCaptor = ArgumentCaptor.forClass(URI.class);

        when(restTemplate.exchange(uriCaptor.capture(), eq(HttpMethod.GET), isNull(HttpEntity.class), eq(TR_QUESTIONS_PAGE)))
                .thenReturn(new ResponseEntity<>(page, HttpStatus.OK));

        final PageDto<QuestionDto> res = cl.search(pageable, intitle, site);
        assertEquals(page, res);

        final URI acUri = uriCaptor.getValue();
        assertEquals(uri, acUri);
    }
}