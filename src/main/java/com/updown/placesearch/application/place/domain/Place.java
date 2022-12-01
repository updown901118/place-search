package com.updown.placesearch.application.place.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Place {
    private final String title;
    @Builder
    public Place(String title) {
        this.title = title;
    }
}

