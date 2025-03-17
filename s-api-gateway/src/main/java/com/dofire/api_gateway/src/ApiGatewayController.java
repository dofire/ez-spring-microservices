package com.dofire.api_gateway.src;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiGatewayController {

    private final RestTemplate restTemplate;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    @GetMapping("/users/{id}")
    public CompletableFuture<String> getUserById(@PathVariable String id) {
        return CompletableFuture.supplyAsync(
                () -> restTemplate.getForObject("http://user-service/users/" + id, String.class),
                executorService
        );
    }
}
