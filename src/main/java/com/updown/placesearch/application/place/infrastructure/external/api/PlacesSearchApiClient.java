package com.updown.placesearch.application.place.infrastructure.external.api;


import java.util.Map;

public interface PlacesSearchApiClient {
    int RESULT_COUNT_LIMIT = 5;

    Map<String, String> requestPlaceSearchApi(String keyword);
}
