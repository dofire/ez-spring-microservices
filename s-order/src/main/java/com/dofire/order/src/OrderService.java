package com.dofire.order.src;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        // Publish event
        OrderEvent event = new OrderEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getStatus()
        );
        orderEventProducer.sendOrderEvent(event);

        return savedOrder;
    }
}