package com.site.rrus.controller;

import com.site.rrus.exception.GlobalException;
import com.site.rrus.hash.Link;
import com.site.rrus.model.LinkRequest;
import com.site.rrus.model.LinkResponse;
import com.site.rrus.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.PERMANENT_REDIRECT;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static reactor.core.publisher.Mono.just;

import static java.lang.String.*;

@RestController
@RequestMapping(value = "/reduse", method = {RequestMethod.GET, RequestMethod.PUT})
@Slf4j
@Validated
public class ReactiveLinkController {

    private LinkService linkService;

    @Autowired
    public ReactiveLinkController(LinkService linkService) {
        this.linkService = linkService;
        log.info(format("Class[%s] initialized", getClass().getSimpleName()));
    }

    @GetMapping
    public Mono<ResponseEntity<String>> welcome() {
        log.info(format("Class[%s],Method[%s]", getClass().getSimpleName(), "welcome"));
        return just(ok(linkService.welcome()));
    }


    @PutMapping(value = "/compact")
    @ResponseBody
    public Mono<ResponseEntity<Object>> putMeLink(@RequestBody LinkRequest request) throws GlobalException {
        String originalLink = request.getLink();
        Link link = linkService.putMeLink(originalLink);
        LinkResponse linkResponse = new LinkResponse(link.getShortenedLink());
        log.info(format("Controller[%s],Method[%s] -> original link[%s], shortened link[%s]"
                , getClass().getSimpleName(), "putMeLink", link.getOriginalLink(), link.getShortenedLink()));
        return just(ok(linkResponse));
    }

    @GetMapping(value = "/extract")
    @ResponseBody
    public Mono<ResponseEntity<Object>> getMeLink(@RequestParam String key) throws GlobalException {
        Link link = linkService.getMeLink(key);
        log.info(format("Controller[%s],Method[%s] -> original link[%s], shortened link[%s]"
                , getClass().getSimpleName(), "getMeLink", link.getOriginalLink(), link.getShortenedLink()));
        return just(status(PERMANENT_REDIRECT).header("Location", link.getOriginalLink()).build())
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}
