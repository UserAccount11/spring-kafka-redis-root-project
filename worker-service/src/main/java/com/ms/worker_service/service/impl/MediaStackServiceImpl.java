package com.ms.worker_service.service.impl;

import com.ms.worker_service.model.exceptions.ExternalApiException;
import com.ms.worker_service.service.MediaStackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaStackServiceImpl implements MediaStackService {

    @Value("${mediastack.api-key}")
    private String apiKey;

    @Value("${mediastack.countries}")
    private String countries;

    @Value("${mediastack.limit}")
    private String limit;

    private final WebClient webClient;

    @Override
    public Mono<ResponseEntity<String>> sendRequest(String date) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("access_key", apiKey)
                .queryParam("countries", countries)
                .queryParam("limit", limit)
                .queryParam("date", date)
                .build())
            .retrieve()
            .onStatus(HttpStatusCode::isError, response ->
                response.bodyToMono(String.class)
                    .flatMap(body -> {
                        log.error("Error with external API - Status: {}, Body: {}", response.statusCode(), body);
                        return Mono.error(new ExternalApiException((HttpStatus) response.statusCode(), body));
                    })
            )
            .toEntity(String.class);
    }
}
