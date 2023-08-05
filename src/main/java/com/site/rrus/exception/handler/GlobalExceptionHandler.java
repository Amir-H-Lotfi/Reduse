package com.site.rrus.exception.handler;

import com.site.rrus.exception.NoSuchShortenedURLFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.io.Serializable;

import static java.lang.String.format;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements Serializable {

    public GlobalExceptionHandler() {
        log.info(format("Exception Handler [%s] initialized!", getClass().getSimpleName()));
    }

    @ExceptionHandler(NoSuchShortenedURLFoundException.class)
    public Mono<ResponseEntity> handleNSSUFE(Exception e) {
        log.error(format("Failed to find related url -> exception message %s", e.getMessage()));
        return Mono.just(ResponseEntity.badRequest().build());
    }
}
