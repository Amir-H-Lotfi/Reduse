package com.site.rrus.service;

import com.site.rrus.exception.GlobalException;
import com.site.rrus.hash.Link;

import java.io.Serializable;

public interface LinkService extends Serializable {

    default String welcome() {
        return new String("Welcome To Reactive Url Shortener");
    }

    Link putMeLink(String link) throws GlobalException;

    Link getMeLink(String link) throws GlobalException;
}
