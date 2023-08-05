package com.site.rrus.repository;

import com.site.rrus.hash.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataRedisTest
class LinkRepositoryTest {

    @Autowired
    private LinkRepository repository;

    @BeforeEach
    void initialize() {
        assertNotNull(repository);
    }

    @Test
    @Rollback
    void save() throws Exception {
        var link = new Link("http://www.google.com", "http://www.reduse.com/test-hash-key");
        var sLink = repository.save(link);
        assertEquals(link, sLink);
    }

    @Test
    void findByKey() throws Exception {
        var key = "http://www.reduse.com/test-hash-key";
        var oLink = repository.findById(key);
        assertThat(oLink).isNotEmpty();
    }

}