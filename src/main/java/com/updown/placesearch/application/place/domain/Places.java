package com.updown.placesearch.application.place.domain;

import lombok.Getter;

import java.util.*;

/**
 * 공백제거된 카카오 검색결과, 공백제거 and 태그제거된 네이버 검색결과를
 * 카카오 검색 결과를 기준으로 두검색 결과에 동일하게 나타나는 문서(장소)가 상위에 올 수 있도록 정렬해주는 결과를 담는 클래스
 * ex) 카카오 검색결과= {A,B,C} , 네이버 검색결과 = {C,D,E}, 최종결과 = {C,A,B,D,E}
 **/
@Getter
public class Places {
    private List<Place> places;

    public Places(Map<String, String> kakaoPlaceNameMap, Map<String, String> naverPlaceNameMap) {
        this.places = combinationPlaceNames(kakaoPlaceNameMap, naverPlaceNameMap);
    }

    private List<Place> combinationPlaceNames(Map<String, String> kakaoPlaceNameMap, Map<String, String> naverPlaceNameMap) {

        // 중복 키를 걸러내서 가져옴
        List<String> duplicateKeys = findDuplicateKey(kakaoPlaceNameMap, naverPlaceNameMap);

        // 중복키로 카카오 검색결과를 가져와서 최종 조합 결과 List에 add 한다
        List<Place> kakaoNaverPlaceNames = findDuplicateValue(kakaoPlaceNameMap, duplicateKeys);

        // 각 맵 마다 중복 키(공백제거, 대문자변환 한 결과 값)를 가진 노드를 삭제한다
        removeDuplicateKeys(kakaoPlaceNameMap, naverPlaceNameMap, duplicateKeys);

        // 중복키가 삭제된 카카오 검색 결과를 최종 조합 결과 List에 add한다
        kakaoNaverPlaceNames.addAll(kaKaoPlaceNameValues(kakaoPlaceNameMap));

        // 중복키가 삭제된 네이버 검색 결과를 최종 조합 결과 List에 add한다
        kakaoNaverPlaceNames.addAll(naverPlaceNameValues(naverPlaceNameMap));

        return kakaoNaverPlaceNames;
    }

    private List<Place> naverPlaceNameValues(Map<String, String> naverPlaceNameMap) {
        List<Place> naverPlaceValues = new ArrayList<>();
        for (String value : naverPlaceNameMap.values()) {
            naverPlaceValues.add(new Place(value));
        }

        return naverPlaceValues;
    }

    private List<Place> kaKaoPlaceNameValues(Map<String, String> kakaoPlaceNameMap) {
        List<Place> kakaoPlaceValues = new ArrayList<>();
        for (String value : kakaoPlaceNameMap.values()) {
            kakaoPlaceValues.add(new Place(value));
        }

        return kakaoPlaceValues;
    }

    private void removeDuplicateKeys(Map<String, String> kakaoPlaceNameMap, Map<String, String> naverPlaceNameMap, List<String> duplicateKeys) {
        for (String duplicateKey : duplicateKeys) {
            kakaoPlaceNameMap.remove(duplicateKey);
            naverPlaceNameMap.remove(duplicateKey);
        }
    }

    private List<Place> findDuplicateValue(Map<String, String> kakaoPlaceNameMap, List<String> duplicateKeys) {
        List<Place> kakaoNaverPlaceNames = new ArrayList<>();

        for (String duplicateKey : duplicateKeys) {
            kakaoNaverPlaceNames.add(new Place(kakaoPlaceNameMap.get(duplicateKey)));
        }

        return kakaoNaverPlaceNames;
    }

    private List<String> findDuplicateKey(Map<String, String> kakaoPlaceNameMap, Map<String, String> naverPlaceNameMap) {
        List<String> duplicateKeys = new ArrayList<>();
        for (String kakaoPlaceNameMapKey : kakaoPlaceNameMap.keySet()) {
            if (naverPlaceNameMap.containsKey(kakaoPlaceNameMapKey)) {
                duplicateKeys.add(kakaoPlaceNameMapKey);
            }
        }

        return duplicateKeys;
    }
}
