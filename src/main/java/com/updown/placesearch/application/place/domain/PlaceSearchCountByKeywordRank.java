package com.updown.placesearch.application.place.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceSearchCountByKeywordRank {
    private String keyword;
    private Long searchCount;
    @Builder
    public PlaceSearchCountByKeywordRank(String keyword, Long searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }
}
