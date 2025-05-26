package com.dofire.payment.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MqOrderConsumer {

    private static final AtomicInteger counter = new AtomicInteger(0);

    @KafkaListener(
            topics = "order-events",
            groupId = "payment-group",
            batch = "true"
    )
    public void consume(
            List<String> messages,
            Acknowledgment ack
    ) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        var subMessages = splitIntoParts(messages, 3);
        for(var i = 0; i<subMessages.size(); i++){
            int finalI = i;
            threads.add(
                    Thread.ofVirtual().start(()->process(subMessages.get(finalI)))
            );
        }

        for(var thread: threads){
            thread.join();
        }

        ack.acknowledge();

    }

    private void process(List<String> messages){

            try {
                Thread.sleep(1000);
                counter.addAndGet(messages.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("<> received " + messages.size() + " orders " + " | " + Thread.currentThread() + " | " + counter);
    }


    public static List<List<String>> splitIntoParts(List<String> input, int parts) {
        int totalSize = input.size();
        int baseSize = totalSize / parts;
        int remainder = totalSize % parts;

        List<List<String>> result = new ArrayList<>();
        int start = 0;

        for (int i = 0; i < parts; i++) {
            int size = baseSize + (i < remainder ? 1 : 0);
            int end = Math.min(start + size, totalSize);
            result.add(input.subList(start, end));
            start = end;
        }

        return result;
    }


}
