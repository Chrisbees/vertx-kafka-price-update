package com.kafka.eureka.priceupdates.config;


import com.kafka.eureka.priceupdates.MainVerticle;
import com.kafka.eureka.priceupdates.verticle.PriceUpdateVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class VertxBootstrap implements CommandLineRunner {

    private final Vertx vertx;
    private final MainVerticle mainVerticle;
    private final PriceUpdateVerticle priceUpdateVerticle;

    @Override
    public void run(String... args) throws Exception {

        Future<String> deployMain = Future.future(promise -> vertx.deployVerticle(mainVerticle));
        Future<String> deployPrice = Future.future(promise -> vertx.deployVerticle(priceUpdateVerticle));

        Future.all(deployMain, deployPrice).onComplete(event -> {
            if (event.succeeded()) {
                log.info("Price Updates Verticle Deployed");
            } else {
                log.error("VertxBootstrap", event.cause());
            }
        });
    }
}
