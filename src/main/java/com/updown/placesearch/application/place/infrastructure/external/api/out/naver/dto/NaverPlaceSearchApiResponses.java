package com.updown.placesearch.application.place.infrastructure.external.api.out.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 네이버에서 제공하는 API Response 템플릿 클래스
 */
@Getter
public class NaverPlaceSearchApiResponses {
    private List<NaverPlaceSearchApiResponse> items;

    public boolean isNotErrorResponses() {
        return this.items != null;
    }
}
