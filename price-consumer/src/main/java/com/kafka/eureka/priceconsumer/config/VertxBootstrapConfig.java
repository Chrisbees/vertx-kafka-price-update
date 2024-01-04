package com.kafka.eureka.priceconsumer.config;


import com.kafka.eureka.priceconsumer.verticle.MainVerticle;
import com.kafka.eureka.priceconsumer.verticle.PriceConsumerVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class VertxBootstrapConfig implements CommandLineRunner {

    private final Vertx vertx;
    private final MainVerticle mainVerticle;
    private final PriceConsumerVerticle priceConsumerVerticle;

    @Override
    public void run(String... args) throws Exception {

        JsonObject jsonObject = new JsonObject()
                .put("name", "Idris Ishaq")
                .put("phoneNumber", "08062831428");

        DeploymentOptions options = new DeploymentOptions()
                .setConfig(jsonObject);

        Future<String> deployPriceVerticle = Future.future(promise -> vertx.deployVerticle(priceConsumerVerticle, options, promise));
        Future<String> deployMainVerticle = Future.future(promise -> vertx.deployVerticle(mainVerticle, options, promise));

        Future.all(deployPriceVerticle, deployMainVerticle)
                .onComplete(compositeFuture -> {
                    if (compositeFuture.succeeded()) {
                        log.info("Main Verticle Deployed");
                    } else {
                        log.info("failed to deploy main verticle");
                    }
                });
    }

}
