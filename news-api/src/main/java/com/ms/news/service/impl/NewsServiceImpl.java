package com.ms.news.service.impl;

import com.ms.news.repository.NewsRepository;
import com.ms.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.ms.news.utils.Constants.TOPIC_NAME;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NewsRepository newsRepository;

    @Override
    public Mono<Void> publishToMessageBroker(String date) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, null, date);
        return Mono.fromFuture(kafkaTemplate.send(record))
                .then();
    }

    @Override
    public Mono<Object> getNews(String date) {
        return newsRepository.getNews(date)
                .flatMap(Mono::just)
                .switchIfEmpty(Mono.defer(() -> publishToMessageBroker(date)));
    }
}
