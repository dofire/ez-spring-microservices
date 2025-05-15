package com.dofire.order.src;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(name = "product_id")
    private UUID productId;
    
    @Column
    private int quantity;
    
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    
    @Column
    private String status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
