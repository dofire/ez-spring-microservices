package com.dofire.order.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqOrderDto {
    private UUID orderId;
    private UUID productId;
    private int quantity;
    private String status;
}