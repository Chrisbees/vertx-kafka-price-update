package com.kafka.eureka.priceconsumer.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class MainVerticle extends AbstractVerticle {

    private final Router router;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        router.route("/websocket").handler(new HandleWebSocket(vertx));
        router.route().handler(StaticHandler.create("static"));

        vertx.createHttpServer().requestHandler(router)
                .listen(9001)
                .onSuccess(success -> {
                    log.info("Server started on port {}", success.actualPort());
                    startPromise.complete();
                }).onFailure(startPromise::fail);

    }
}
