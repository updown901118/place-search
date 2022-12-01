package com.updown.placesearch.application.place.service;

import com.updown.placesearch.application.place.domain.PlaceSearchCountByKeywordRank;
import com.updown.placesearch.application.place.infrastructure.repository.PlaceSearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceSearchHistoryService {

    public static final int LIMIT_SIZE = 10;
    public static final int PAGE = 0;
    private final PlaceSearchHistoryRepository placeSearchHistoryRepository;

    public List<PlaceSearchCountByKeywordRank> findTop10SearchCountByKeywordRank() {
        return placeSearchHistoryRepository.findTop10SearchCountByKeywordRank(PageRequest.of(PAGE, LIMIT_SIZE));
    }
}
