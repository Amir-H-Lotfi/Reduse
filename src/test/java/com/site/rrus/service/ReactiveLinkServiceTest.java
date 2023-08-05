package com.site.rrus.service;

import com.site.rrus.exception.GlobalException;
import com.site.rrus.hash.Link;
import com.site.rrus.repository.LinkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReactiveLinkServiceTest {

    private LinkRepository repository = Mockito.mock(LinkRepository.class);
    private LinkService linkService = new ReactiveLinkService(repository, "http://www.reduse.com/");
    private Link link = new Link("http://www.google.com", "http://www.reduse.com/");

    @Test
    void putMeLink() throws GlobalException {
        when(repository.save(any())).thenReturn(link);
        Link result = linkService.putMeLink(link.getOriginalLink());
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(link);
    }

    @Test
    void getMeLink() throws GlobalException {
        when(repository.findById(any())).thenReturn(Optional.of(link));
        Link result = linkService.getMeLink(link.getShortenedLink());
        Assertions.assertThat(result).usingRecursiveAssertion().isEqualTo(link);
    }
}