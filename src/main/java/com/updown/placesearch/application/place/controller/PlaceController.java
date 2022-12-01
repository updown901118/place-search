package com.updown.placesearch.application.place.controller;

import com.updown.placesearch.application.place.domain.Places;
import com.updown.placesearch.application.place.service.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceSearchService placeSearchService;

    @GetMapping("/place")
    public ResponseEntity<Places> findPlaces(String keyword) {
        return ResponseEntity.ok(placeSearchService.findPlaces(keyword));
    }
}
