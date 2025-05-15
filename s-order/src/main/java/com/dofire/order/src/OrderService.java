package com.dofire.order.src;

import com.dofire.order.rpc.RpcStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    private final RpcStockService rpcStockService;

    public boolean createOrder(
            OrderDto request
    ) {

        return rpcStockService.reserveStock(request.getProductId(), request.getQuantity());

//        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
//
//        // Publish event
//        OrderEventDto event = new OrderEventDto(
//                savedOrderEntity.getId(),
//                savedOrderEntity.getProductId(),
//                savedOrderEntity.getQuantity(),
//                savedOrderEntity.getStatus()
//        );
//        orderEventProducer.sendOrderEvent(event);

//        return savedOrderEntity;
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}