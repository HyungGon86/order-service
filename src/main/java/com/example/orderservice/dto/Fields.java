package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class Fields {

    private String type;
    private boolean optional;
    private String field;

    @Builder
    public Fields(String type, boolean optional, String field) {
        this.type = type;
        this.optional = optional;
        this.field = field;
    }

}
