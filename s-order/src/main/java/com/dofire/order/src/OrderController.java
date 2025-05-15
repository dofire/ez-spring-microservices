package com.dofire.order.src;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody OrderDto request
    ) {
        var ok = orderService.createOrder(request);

        if (ok) {
            return ResponseEntity.ok("success");
        }

        return ResponseEntity.ok("error");
    }

    @GetMapping("ok")
    public ResponseEntity<String> getOk() {
        return ResponseEntity.ok("ok");
    }
}
