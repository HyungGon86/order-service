package com.example.orderservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class KafkaOrderDto implements Serializable {

    private Schema schema;
    private Payload payload;

    public KafkaOrderDto(Schema schema, Payload payload) {
        this.schema = schema;
        this.payload = payload;
    }
}
