package com.site.rrus.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

import static java.lang.String.*;

@Configuration
@EnableWebFlux
@Slf4j
public class ApplicationConfiguration {

    public ApplicationConfiguration() {
        log.info(format("Class[%s] configured", getClass().getSimpleName()));
    }
}
