package com.updown.placesearch.application.place.dto;

import com.updown.placesearch.application.place.domain.Places;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class PlacesTest {

    /**
     공백제거된 카카오 검색결과, 공백제거 and 태그제거된 네이버 검색결과를
     카카오 검색 결과를 기준으로 두검색 결과에 동일하게 나타나는 문서(장소)가 상위에 올 수 있도록 정렬하는기능
     ex) 카카오 검색결과= {A,B,C} , 네이버 검색결과 = {C,D,E}, 최종결과 = {C,A,B,D,E}
     **/
    @Test
    void 검색결과_조합_정렬_테스트() {
        Map<String, String> kakaoPlaceNameMap = new HashMap<>();
        kakaoPlaceNameMap.put("A", "A");
        kakaoPlaceNameMap.put("B", "B");
        kakaoPlaceNameMap.put("C", "C");

        Map<String, String> naverPlaceNameMap = new HashMap<>();
        naverPlaceNameMap.put("C", "C");
        naverPlaceNameMap.put("D", "D");
        naverPlaceNameMap.put("E", "E");

        Places places = new Places(kakaoPlaceNameMap, naverPlaceNameMap);
        assertThat(places.getPlaces().get(0).getTitle()).isEqualTo("C");
        assertThat(places.getPlaces().get(1).getTitle()).isEqualTo("A");
        assertThat(places.getPlaces().get(2).getTitle()).isEqualTo("B");
        assertThat(places.getPlaces().get(3).getTitle()).isEqualTo("D");
        assertThat(places.getPlaces().get(4).getTitle()).isEqualTo("E");
    }

    @Test
    void 검색결과_조합_시_사용자에게_보여지는_중복값_띄어쓰기는_카카오_기준으로_보여지는지_테스트 () {
        Map<String, String> kakaoPlaceNameMap = new HashMap<>();
        kakaoPlaceNameMap.put("ABC", "A BC");
        kakaoPlaceNameMap.put("BCD", "B CD");
        kakaoPlaceNameMap.put("CDE", "C DE");

        Map<String, String> naverPlaceNameMap = new HashMap<>();
        naverPlaceNameMap.put("CDE", "CDE");
        naverPlaceNameMap.put("EFG", "E FG");
        naverPlaceNameMap.put("HIJ", "H IJ");

        Places places = new Places(kakaoPlaceNameMap, naverPlaceNameMap);
        assertThat(places.getPlaces().get(0).getTitle()).isEqualTo("C DE");
        assertThat(places.getPlaces().get(1).getTitle()).isEqualTo("A BC");
        assertThat(places.getPlaces().get(2).getTitle()).isEqualTo("B CD");
        assertThat(places.getPlaces().get(3).getTitle()).isEqualTo("E FG");
        assertThat(places.getPlaces().get(4).getTitle()).isEqualTo("H IJ");
    }
}
