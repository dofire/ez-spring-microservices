package com.dofire.product.rpc;

import com.dofire.order.StockServiceGrpc;
import com.dofire.order.StockServiceOuterClass.*;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class RpcStockService extends StockServiceGrpc.StockServiceImplBase {

    @Override
    public void reserveStock(
            ReserveStockRequest request,
            StreamObserver<ReserveStockResponse> responseObserver
    ) {
        String id = request.getProductId();
        int quantity = request.getQuantity();

        var res = ReserveStockResponse.newBuilder();

        System.out.println("Product ID: " + id);

        if (quantity > 1){
            res.setSuccess(false);
        } else {
            res.setSuccess(true);
        }

        responseObserver.onNext(res.build());
        responseObserver.onCompleted();
    }

}
