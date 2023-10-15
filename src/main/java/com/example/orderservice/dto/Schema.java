package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class Schema {

    private String type;
    private List<Fields> fields;
    private boolean optional;
    private String name;

    @Builder
    public Schema(String type, List<Fields> fields, boolean optional, String name) {
        this.type = type;
        this.fields = fields;
        this.optional = optional;
        this.name = name;
    }
}
