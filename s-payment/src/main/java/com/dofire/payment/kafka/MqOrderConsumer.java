package com.dofire.payment.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MqOrderConsumer {

    private static int counter = 0;

    @KafkaListener(
            topics = "order-events",
            groupId = "payment-group",
            batch = "true"
    )
    public void consume(List<String> messages, Acknowledgment ack){
        Thread.ofVirtual().start(()->{
            try {
                Thread.sleep(1000);
                counter += messages.size();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("<> received " + messages.size() + " orders " + " | " + Thread.currentThread() + " | " + counter);
            ack.acknowledge();
        });
    }

}
