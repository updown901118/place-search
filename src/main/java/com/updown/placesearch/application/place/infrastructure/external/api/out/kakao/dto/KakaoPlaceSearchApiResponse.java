package com.updown.placesearch.application.place.infrastructure.external.api.out.kakao.dto;

import lombok.Getter;

/**
 * 카카오에서 제공하는 키워드로 장소 검색하기 API 응답값을 담기 위한 클래스
 * URL: https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword-request
 */
@Getter
public class KakaoPlaceSearchApiResponse {
    public static final String BLANK = "";
    public static final String SPACE = " ";
    private String place_name; // 장소명
    private String remove_blank_place_name; // 공백제거, 대문자변환 장소명

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
        this.remove_blank_place_name = place_name.replaceAll(SPACE, BLANK) // 공백 제거
                .toUpperCase();
    }
}
