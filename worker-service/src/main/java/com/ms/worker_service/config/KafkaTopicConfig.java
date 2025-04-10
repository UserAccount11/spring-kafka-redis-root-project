package com.ms.worker_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.ms.worker_service.utils.Constants.TOPIC_NAME;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic newsTopic() {
        return TopicBuilder.name(TOPIC_NAME).build();
    }

}
