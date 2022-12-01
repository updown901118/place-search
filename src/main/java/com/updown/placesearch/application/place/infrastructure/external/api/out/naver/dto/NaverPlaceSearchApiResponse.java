package com.updown.placesearch.application.place.infrastructure.external.api.out.naver.dto;

import lombok.Getter;
import lombok.ToString;

/**
 * 네이버에서 제공하는 키워드로 장소 검색하기 API 응답값을 담기 위한 클래스
 * URL: https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD
 */
@Getter
public class NaverPlaceSearchApiResponse {
    public static final String BLANK = "";
    public static final String SPACE = " ";
    public static final String REGEX_HTML_TAG_REMOVE = "\\<.*?>"; // HTML 태그 제거 정규식
    private String title; // 장소명
    private String removeBlankTitle; // 공백제거, 태그제거, 대문자변환한 장소명

    public void setTitle(String title) {
        this.title = title.replaceAll(REGEX_HTML_TAG_REMOVE, BLANK);// HTML 태그 제거
        this.removeBlankTitle = title.replaceAll(REGEX_HTML_TAG_REMOVE, BLANK) // HTML 태그 제거
                .replaceAll(SPACE, BLANK) // 공백 제거
                .toUpperCase();
    }
}
