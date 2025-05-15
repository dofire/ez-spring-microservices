package com.dofire.order.src;

import com.dofire.order.kafka.MqOrderDto;
import com.dofire.order.kafka.MqOrderProducer;
import com.dofire.order.rpc.RpcStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MqOrderProducer mqOrderProducer;

    private final RpcStockService rpcStockService;

    public boolean createOrder(
            OrderDto request
    ) {

        var ok = rpcStockService.reserveStock(
                request.getProductId().toString(),
                request.getQuantity()
        );

        if (!ok) {
            // Stock not available, show error message to user
            return false;
        }

        var orderEntity = OrderEntity.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .totalPrice(BigDecimal.ZERO)
                .status(OrderStatus.PENDING.toString())
                .build();

        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

        // Publish event
        MqOrderDto event = new MqOrderDto(
                savedOrderEntity.getId(),
                savedOrderEntity.getProductId(),
                savedOrderEntity.getQuantity(),
                savedOrderEntity.getStatus()
        );
        mqOrderProducer.sendOrderEvent(event);

        return true;
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}