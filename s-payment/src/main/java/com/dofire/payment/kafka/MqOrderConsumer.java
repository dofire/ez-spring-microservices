package com.dofire.payment.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MqOrderConsumer {

    @KafkaListener(topics = "order-events", groupId = "payment-group", concurrency = "10")
    public void consume(String message){
        Thread.ofVirtual().start(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("<> received order: " + message + " | " + Thread.currentThread());
        });
    }

}
