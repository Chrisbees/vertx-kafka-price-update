package com.kafka.eureka.priceconsumer.verticle;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class HandleWebSocket implements Handler<RoutingContext> {

    private final Vertx vertx;
    private final List<JsonObject> updateList = new ArrayList<>();

    @Override
    public void handle(RoutingContext context) {
        Future<ServerWebSocket> fut = context.request().toWebSocket();
        fut.onSuccess(socket -> {
            socket.accept();
            vertx.eventBus().consumer("price-updates", message -> {
                String update = message.body().toString();
                socket.writeTextMessage(update);
                vertx.eventBus().publish("/topic/price-updates", update);
            });
            log.info("connected");
            socket.exceptionHandler(err -> log.info("Connection failed {0}", err));
            socket.endHandler(onClose -> {
                log.info("Connection closed {}", onClose);
                socket.close();
            });
        });
    }


    public JsonObject setUpdates(JsonObject updates){
       log.info("updates" + updates);
       this.updateList.add(updates);
       return updates;
    }
}
