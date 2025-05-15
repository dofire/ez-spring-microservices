package com.dofire.order.src;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    private final KafkaTemplate<String, OrderEventDto> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEventDto event) {
        kafkaTemplate.send("order-events", event);
    }
}