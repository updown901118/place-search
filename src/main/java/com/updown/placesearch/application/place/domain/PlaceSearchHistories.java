package com.updown.placesearch.application.place.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PlaceSearchHistories {
    private List<PlaceSearchCountByKeywordRank> placeSearchHistories;

    @Builder
    public PlaceSearchHistories(List<PlaceSearchCountByKeywordRank> placeSearchHistories) {
        this.placeSearchHistories = placeSearchHistories;
    }
}
