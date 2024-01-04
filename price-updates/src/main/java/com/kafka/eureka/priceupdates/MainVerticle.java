package com.kafka.eureka.priceupdates;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MainVerticle extends AbstractVerticle {

    private final Router router;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        vertx.createHttpServer().requestHandler(router)
                .listen(9000)
                .onSuccess(success -> {
                    log.info("Server started on port {}", success.actualPort());
                    startPromise.complete();
                }).onFailure(startPromise::fail);

    }
}
