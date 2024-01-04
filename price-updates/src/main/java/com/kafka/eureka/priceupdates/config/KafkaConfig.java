package com.kafka.eureka.priceupdates.config;

import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

//    private NewTopic topic(){
//        return TopicBuilder
//                .name(PRICE_UPDATES)
//                .build();
//    }

    @Bean
    @Autowired
    public KafkaProducer<String, Object> setConfig(Vertx vertx) {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "Chrisbees:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return KafkaProducer.create(vertx, config);
    }


}
