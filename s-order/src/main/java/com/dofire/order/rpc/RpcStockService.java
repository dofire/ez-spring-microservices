package com.dofire.order.rpc;


import com.dofire.order.StockServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.dofire.order.StockServiceOuterClass.*;
import org.springframework.stereotype.Component;

@Component
public class RpcStockService {

    private final StockServiceGrpc.StockServiceBlockingStub stub;

    public RpcStockService() {
        final String host = "localhost";
        final int port = 9090;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        stub = StockServiceGrpc.newBlockingStub(channel);

    }

    public boolean reserveStock(String productId, int quantity) {
        ReserveStockRequest request = ReserveStockRequest.newBuilder()
                .setProductId(productId)
                .setQuantity(quantity)
                .build();

        return stub.reserveStock(request).getSuccess();
    }
}
