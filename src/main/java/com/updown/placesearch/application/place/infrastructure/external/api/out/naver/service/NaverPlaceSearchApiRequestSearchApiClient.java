package com.updown.placesearch.application.place.infrastructure.external.api.out.naver.service;

import com.updown.placesearch.application.place.infrastructure.external.api.PlacesSearchApiClient;
import com.updown.placesearch.application.place.infrastructure.external.api.out.naver.dto.NaverPlaceSearchApiResponse;
import com.updown.placesearch.application.place.infrastructure.external.api.out.naver.dto.NaverPlaceSearchApiResponses;
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
public class NaverPlaceSearchApiRequestSearchApiClient implements PlacesSearchApiClient {

    public static String QUERY_PARAM_KEY_QUERY = "query";
    public static final String QUERY_PARAM_KEY_DISPLAY = "display";
    public static final String SEARCH_PLACE_API_PATH = "/search/local.json";
    private final WebClient naverApiWebClient;

    public Map<String, String> requestPlaceSearchApi(String keyword) {
        NaverPlaceSearchApiResponses naverPlaceSearchApiResponses = requestPlaceSearchApi(keyword, naverApiWebClient);
        return naverPlaceSearchApiResponses.isNotErrorResponses() ? getPlaceNameMap(naverPlaceSearchApiResponses) : new LinkedHashMap<>();
    }

    private static Map<String, String> getPlaceNameMap(NaverPlaceSearchApiResponses naverPlaceSearchApiResponses) {
        // 저장 순서를 유지하기 위해 LinkedHashMap 사용
        Map<String, String> placeNameMap = new LinkedHashMap<>();

        for (NaverPlaceSearchApiResponse item : naverPlaceSearchApiResponses.getItems()) {
            placeNameMap.put(item.getRemoveBlankTitle(), item.getTitle());
        }
        return placeNameMap;
    }

    private NaverPlaceSearchApiResponses requestPlaceSearchApi(String keyword, WebClient client) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(SEARCH_PLACE_API_PATH)
                        .queryParam(QUERY_PARAM_KEY_QUERY, keyword)
                        .queryParam(QUERY_PARAM_KEY_DISPLAY, RESULT_COUNT_LIMIT)
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    // TODO: 로그만 찍지않고 메신져로 알림 받을수 있는 기능 추가하여 즉각대응
                    log.error("Naver RequestPlaceSearchApi Error response [ param: {}, statusCode: {}, body {} ] : ", keyword, clientResponse.statusCode(), clientResponse.bodyToMono(String.class));
                    return null;
                })
                .bodyToMono(NaverPlaceSearchApiResponses.class)
                .onErrorReturn(new NaverPlaceSearchApiResponses())
                .block();
    }
}
