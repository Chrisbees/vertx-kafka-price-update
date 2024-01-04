package com.kafka.eureka.priceupdates.verticle;

import com.kafka.eureka.priceupdates.model.Products;
import com.kafka.eureka.priceupdates.service.KafkaServiceInterface;
import com.kafka.eureka.priceupdates.service.ProductsDataInterface;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class PriceUpdateVerticle extends AbstractVerticle {

    private final KafkaServiceInterface kafkaService;
    private final ProductsDataInterface products;

    private final Random random = new Random();

    @Autowired
    public PriceUpdateVerticle(KafkaServiceInterface kafkaService, ProductsDataInterface products) {
        this.kafkaService = kafkaService;
        this.products = products;
    }

    @Override
    public void start() throws Exception {
        vertx.setPeriodic(2000, event -> {
            List<Products> productList = products.getProductList()
                    .parallelStream().map(product -> {
                        Double value = 10000 + random.nextDouble(90000);
                        product.setPrice(value);
                        return product;
                    }).collect(Collectors.toList());

            JsonObject jsonObject = new JsonObject()
                    .put("productList", productList);

            kafkaService.sendPriceUpdates(jsonObject.encode());
        });
    }

}
