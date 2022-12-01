package com.updown.placesearch.application.place.infrastructure.external.api.out.kakao.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KakaoPlaceSearchApiResponseTest {

    @Test
    void 공백_제거_대문자변환_테스트() {
        KakaoPlaceSearchApiResponse kakaoPlaceSearchApiResponse = new KakaoPlaceSearchApiResponse();
        kakaoPlaceSearchApiResponse.setPlace_name("가 나 다 라 마 a");
        assertThat(kakaoPlaceSearchApiResponse.getRemove_blank_place_name()).isEqualTo("가나다라마A");
    }

}
