package com.example.orderservice.dto;

import com.example.orderservice.domain.Orders;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseOrderDto implements Serializable {

    private String productId;
    private int qty;
    private int unitPrice;
    private int totalPrice;
    private Long userId;
    private byte[] orderId;

    @Builder
    public ResponseOrderDto(Orders orders) {
        this.productId = orders.getProductId();
        this.qty = orders.getQty();
        this.unitPrice = orders.getUnitPrice();
        this.totalPrice = orders.getTotalPrice();
        this.userId = orders.getUserId();
        UUID uuid = UUID.randomUUID();
        this.orderId = ByteBuffer.wrap(new byte[16]).putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits()).array();
    }

}
