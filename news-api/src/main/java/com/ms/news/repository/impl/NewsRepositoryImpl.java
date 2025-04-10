package com.ms.news.repository.impl;

import com.ms.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {

    private final ReactiveRedisOperations<String, Object> redisOperations;

    @Override
    public Mono<Object> getNews(String date) {
        return redisOperations.opsForValue().get(date);
    }
}
