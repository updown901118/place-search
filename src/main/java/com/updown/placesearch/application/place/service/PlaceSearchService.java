package com.updown.placesearch.application.place.service;

import com.updown.placesearch.application.place.domain.Places;
import com.updown.placesearch.application.place.infrastructure.external.api.PlacesSearchApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlaceSearchService {

    // 하나의 인터페이스의 구현체가 두개라면 변수명으로도 구분하여 주입받을수있어 이렇게 구현
    private final PlacesSearchApiClient kakaoPlaceSearchApiRequestSearchApiClient;
    private final PlacesSearchApiClient naverPlaceSearchApiRequestSearchApiClient;
    private final RedisTemplate redisTemplate;

    public Places findPlaces(String keyword) {

        redisTemplate.opsForValue().increment(keyword);
        Map<String, String> kakaoPlaceNames = kakaoPlaceSearchApiRequestSearchApiClient.requestPlaceSearchApi(keyword);
        Map<String, String> naverPlaceNames = naverPlaceSearchApiRequestSearchApiClient.requestPlaceSearchApi(keyword);

        return new Places(kakaoPlaceNames, naverPlaceNames);
    }
}
