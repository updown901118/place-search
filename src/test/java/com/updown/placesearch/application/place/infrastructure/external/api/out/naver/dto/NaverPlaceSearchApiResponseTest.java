package com.updown.placesearch.application.place.infrastructure.external.api.out.naver.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NaverPlaceSearchApiResponseTest {

    @Test
    void 공백_태그_제거_대문자변환_테스트() {
        NaverPlaceSearchApiResponse naverPlaceSearchApiResponse = new NaverPlaceSearchApiResponse();
        naverPlaceSearchApiResponse.setTitle("<b>가 나 다 라 마a</b>");
        assertThat(naverPlaceSearchApiResponse.getRemoveBlankTitle()).isEqualTo("가나다라마A");
    }

}
