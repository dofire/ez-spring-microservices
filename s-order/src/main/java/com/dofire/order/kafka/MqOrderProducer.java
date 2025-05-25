package com.dofire.order.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqOrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MqOrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(MqOrderDto event) {
        kafkaTemplate.send(
                "order-events",
                event.getOrderId().toString()
        );
    }
}