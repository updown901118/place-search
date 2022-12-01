package com.updown.placesearch.application.place.infrastructure.external.api.out.kakao.service;

import com.updown.placesearch.application.place.infrastructure.external.api.out.kakao.dto.KakaoPlaceSearchApiResponse;
import com.updown.placesearch.application.place.infrastructure.external.api.out.kakao.dto.KakaoPlaceSearchApiResponses;
import com.updown.placesearch.application.place.infrastructure.external.api.PlacesSearchApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoPlaceSearchApiRequestSearchApiClient implements PlacesSearchApiClient {

    public static String QUERY_PARAM_KEY_QUERY = "query";
    public static final String SIZE = "size";
    public static final String SEARCH_PLACE_API_PATH = "/local/search/keyword.json";
    private final WebClient kakaoApiWebClient;

    public Map<String, String> requestPlaceSearchApi(String keyword) {
        KakaoPlaceSearchApiResponses kakaoPlaceSearchApiResponses = requestPlaceSearchApi(keyword, kakaoApiWebClient);
        return kakaoPlaceSearchApiResponses.isNotErrorResponses() ? getPlaceNameMap(kakaoPlaceSearchApiResponses) : new LinkedHashMap<>();
    }

    private static Map<String, String> getPlaceNameMap(KakaoPlaceSearchApiResponses kakaoPlaceSearchApiResponses) {
        // 저장 순서를 유지하기 위해 LinkedHashMap 사용
        Map<String, String> placeNameMap = new LinkedHashMap<>();

        for (KakaoPlaceSearchApiResponse document : kakaoPlaceSearchApiResponses.getDocuments()) {
            placeNameMap.put(document.getRemove_blank_place_name(), document.getPlace_name());
        }

        return placeNameMap;
    }

    private KakaoPlaceSearchApiResponses requestPlaceSearchApi(String keyword, WebClient client) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(SEARCH_PLACE_API_PATH)
                        .queryParam(QUERY_PARAM_KEY_QUERY, keyword)
                        .queryParam(SIZE, RESULT_COUNT_LIMIT)
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    // TODO: 로그만 찍지않고 메신져로 알림 받을수 있는 기능 추가하여 즉각대응
                    log.error("Kakao RequestPlaceSearchApi Error response [ param: {}, statusCode: {}, body {} ] : ", keyword, clientResponse.statusCode(), clientResponse.bodyToMono(String.class));
                    return null;
                })
                .bodyToMono(KakaoPlaceSearchApiResponses.class)
                .onErrorReturn(new KakaoPlaceSearchApiResponses())
                .block();
    }

}
