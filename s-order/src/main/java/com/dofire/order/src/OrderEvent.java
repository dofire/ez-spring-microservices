package com.dofire.order.src;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private UUID orderId;
    private UUID userId;
    private UUID productId;
    private int quantity;
    private String status;
}