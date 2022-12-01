package com.updown.placesearch.application.place.infrastructure.repository;

import com.updown.placesearch.application.place.domain.PlaceSearchCountByKeywordRank;
import com.updown.placesearch.application.place.infrastructure.entity.PlaceSearchHistoryEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceSearchHistoryRepository extends JpaRepository<PlaceSearchHistoryEntity, Long> {
    @Query(value =
        "SELECT new com.updown.placesearch.application.place.domain.PlaceSearchCountByKeywordRank(p.keyword, SUM(p.searchCount))" +
        "FROM place_search_history p GROUP BY p.keyword ORDER BY SUM(p.searchCount) DESC, p.keyword ASC"
    )
    List<PlaceSearchCountByKeywordRank> findTop10SearchCountByKeywordRank(Pageable pageable);
}
