package com.site.rrus.service;

import com.site.rrus.exception.GlobalException;
import com.site.rrus.exception.NoSuchShortenedURLFoundException;
import com.site.rrus.hash.Link;
import com.site.rrus.repository.LinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static java.lang.String.format;

@Service
@Slf4j
public class ReactiveLinkService implements LinkService, Serializable {

    private LinkRepository linkRepository;
    private String baseUrl;

    @Autowired
    public ReactiveLinkService(LinkRepository linkRepository, @Value("${application.baseUrl}") String baseUrl) {
        this.linkRepository = linkRepository;
        this.baseUrl = baseUrl;
        log.info(format("Class[%s] initialized!", getClass().getName()));
    }


    @Override
    public Link putMeLink(String originalLink) throws GlobalException {
        String key = baseUrl + RandomStringUtils.randomAlphabetic(6);
        log.info(format("Service[%s],Method[%s] => shortenedLink[%s]", getClass().getSimpleName(), "putMeLink", key));
        Link link = new Link(originalLink, key);
        Link sLink = linkRepository.save(link);
        log.info(format("Service[%s],Method[%s] => originalLink[%s]", getClass().getSimpleName(), "putMeLink", originalLink));
        return sLink;
    }

    @Override
    public Link getMeLink(String link) throws GlobalException {
        Link result = linkRepository.findById(link).orElseThrow(NoSuchShortenedURLFoundException::new);
        log.info(format("Service[%s],Method[%s] => shortenedLink[%s]", getClass().getSimpleName(), "getMeLink", link));
        return result;
    }


}
