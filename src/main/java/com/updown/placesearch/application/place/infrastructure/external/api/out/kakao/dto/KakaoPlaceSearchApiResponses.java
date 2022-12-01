package com.updown.placesearch.application.place.infrastructure.external.api.out.kakao.dto;

import lombok.Getter;

import java.util.List;

/**
 * 카카오에서 제공하는 API Response 템플릿 클래스
 *
 */
@Getter
public class KakaoPlaceSearchApiResponses {
    private List<KakaoPlaceSearchApiResponse> documents;

    public boolean isNotErrorResponses() {
        return this.documents != null;
    }
}
