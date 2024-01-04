package com.kafka.eureka.priceupdates.service;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.kafka.eureka.priceupdates.constant.Constants.PRICE_UPDATES;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService implements KafkaServiceInterface {

    private final KafkaProducer<String, Object> kafkaProducer;

    @Override
    public void sendPriceUpdates(String data) {

        KafkaProducerRecord<String, Object> record =
                KafkaProducerRecord.create(PRICE_UPDATES, data);

        kafkaProducer.write(record).onSuccess(success -> {
            log.info("Message " + record.value());
        });
    }

}
