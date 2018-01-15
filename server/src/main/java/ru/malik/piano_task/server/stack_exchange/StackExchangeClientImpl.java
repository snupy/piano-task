package ru.malik.piano_task.server.stack_exchange;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StackExchangeClientImpl implements StackExchangeClient {

    private static final String SEARCH_URL = "http://api.stackexchange.com/2.2/search";
    private static final String SEARCH_FILTER_1 = "!*IXk1kQqzsc8kyMHSIZp7Ywi-mODTKQTFm18PsvR4OBIEB(vKZIPfYsKDDqkIW";
    static final ParameterizedTypeReference<PageDto<QuestionDto>> TR_QUESTIONS_PAGE =
            new ParameterizedTypeReference<PageDto<QuestionDto>>() {
            };


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PageDto<QuestionDto> search(Pageable pageable, String intitle, String site) {
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(SEARCH_URL)
                .queryParam("page", pageable.getPageNumber() + 1)
                .queryParam("pagesize", pageable.getPageSize())
                .queryParam("intitle", intitle)
                .queryParam("site", site)
                .queryParam("filter", SEARCH_FILTER_1);

        ResponseEntity<PageDto<QuestionDto>> resp = restTemplate.exchange(uriComponentsBuilder.build().toUri(),
                HttpMethod.GET, null, TR_QUESTIONS_PAGE);
        return resp.getBody();
    }
}
