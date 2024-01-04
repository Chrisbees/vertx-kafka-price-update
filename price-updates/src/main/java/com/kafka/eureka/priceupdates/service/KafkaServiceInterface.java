package com.kafka.eureka.priceupdates.service;

public interface KafkaServiceInterface {
    void sendPriceUpdates(String data);
}
