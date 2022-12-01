package com.updown.placesearch.application.place.controller;

import com.updown.placesearch.application.place.domain.PlaceSearchCountByKeywordRank;
import com.updown.placesearch.application.place.service.PlaceSearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceSearchHistoryController {

    private final PlaceSearchHistoryService placeSearchHistoryService;

    @GetMapping("/searchCountByKeywordTop10Rank")
    public ResponseEntity<List<PlaceSearchCountByKeywordRank>> findTop10SearchCountByKeywordRank() {
        return ResponseEntity.ok().body(placeSearchHistoryService.findTop10SearchCountByKeywordRank());
    }
}
