package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RequestOrderDto {

    private String productId;
    private int qty;
    private int unitPrice;
    private int totalPrice;

    public RequestOrderDto() {
    }

    @Builder
    public RequestOrderDto(String productId, int qty, int unitPrice) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = this.qty * this.unitPrice;
    }

}
