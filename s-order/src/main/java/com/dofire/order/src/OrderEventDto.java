package com.dofire.order.src;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventDto {
    private UUID orderId;
    private UUID productId;
    private int quantity;
    private String status;
}