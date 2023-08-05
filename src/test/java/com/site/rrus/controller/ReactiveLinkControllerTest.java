package com.site.rrus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.rrus.hash.Link;
import com.site.rrus.model.LinkRequest;
import com.site.rrus.service.LinkService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ReactiveLinkController.class)
class ReactiveLinkControllerTest {

    @Autowired
    private WebTestClient client;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private LinkService linkService;

    private String originalLink = "http://www.google.com/";
    private String shortenedLink = "http://www.reduse.com/";
    private Link link = new Link(originalLink, shortenedLink + "key01");

    /**
     * Always prefer to use UriBuilder for controller testing, I love it
     * @throws Exception
     */
    @Test
    void putMeLink() throws Exception {

        when(linkService.putMeLink(link.getOriginalLink())).thenReturn(link);

        client.put()
                .uri(builder -> builder
                        .scheme("http")
                        .host("localhost")
                        .port(8080)
                        .path("/reduse/compact")
                        .build()
                ).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mapper.writeValueAsString(new LinkRequest(originalLink)))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().jsonPath("$.shortenedLink").value(value -> assertThat(value).isEqualTo(link.getShortenedLink()));
    }

    @Test
    void getMeLink() throws Exception {

        when(linkService.getMeLink(link.getShortenedLink())).thenReturn(link);
        client.get()
                .uri(builder -> builder
                        .host("localhost")
                        .port(8080)
                        .path("/reduse/extract")
                        .queryParam("key", link.getShortenedLink())
                        .build())
                .exchange()
                .expectStatus().isPermanentRedirect()
                .expectHeader().value("Location", location -> Assertions.assertThat(location).isEqualTo(originalLink));

    }

}