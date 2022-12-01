package com.updown.placesearch.common.scheduler;

import com.updown.placesearch.application.place.infrastructure.entity.PlaceSearchHistoryEntity;
import com.updown.placesearch.application.place.infrastructure.repository.PlaceSearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlaceSearchHistoryScheduler {
    private final PlaceSearchHistoryRepository placeSearchHistoryRepository;
    private final RedisTemplate redisTemplate;

    @Scheduled(fixedDelay = 5000)
    public void savePlaceSearchHistory() {

        // 현재 redis에 들어가있는 전체 키를 가져온다
        Set<String> keys = redisTemplate.keys("*");

        // 전체 키를 List<Entity> 로 변환한다
        List<PlaceSearchHistoryEntity> placeSearchHistoryEntities = new ArrayList<>();
        for (String key : keys) {
            placeSearchHistoryEntities.add(PlaceSearchHistoryEntity.builder()
                    .keyword(key)
                    .searchCount(Long.valueOf(redisTemplate.opsForValue().get(key).toString()))
                    .build());
        }

        // 변환한 Entity를 전체 저장한다
        placeSearchHistoryRepository.saveAll(placeSearchHistoryEntities);
        redisTemplate.delete(keys);
    }
}
