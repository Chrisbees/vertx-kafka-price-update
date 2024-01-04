package com.kafka.eureka.priceconsumer.verticle;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class PriceConsumerVerticle extends AbstractVerticle {
    public static final String PRICE_UPDATES = "price-updates";
    private final KafkaConsumer<String, Object> consumer;
    private final HandleWebSocket webSocket;

    @Override
    public void start(){

        consumer.subscribe(PRICE_UPDATES, res -> {
            if (res.succeeded()) {
                log.info("Successfully Subscribed to topic");

                consumer.handler(records -> {
                    String updates = records.value().toString();
                    JsonObject jsonObject = new JsonObject(updates);
                    log.info(jsonObject.encodePrettily());
                    webSocket.setUpdates(jsonObject);
                    vertx.eventBus().publish("price-updates", jsonObject);
                });
            } else {
                log.info("Failed to subscribe");
            }
        });
    }
}
