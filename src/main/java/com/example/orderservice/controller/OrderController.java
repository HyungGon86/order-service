package com.example.orderservice.controller;

import com.example.orderservice.domain.Orders;
import com.example.orderservice.dto.RequestOrderDto;
import com.example.orderservice.dto.ResponseOrderDto;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in Order Service";
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrderDto> createOrder(@PathVariable Long userId,
                                                        @RequestBody RequestOrderDto requestOrder) {
        /* jpa 사용 */
        /* ResponseOrderDto order = orderService.createOrder(requestOrder, userId);*/

        /* kafka */
        Orders orders = Orders.builder()
                .productId(requestOrder.getProductId())
                .qty(requestOrder.getQty())
                .unitPrice(requestOrder.getUnitPrice())
                .userId(userId)
                .build();

        ResponseOrderDto order = new ResponseOrderDto(orders);

        /* send this order to the kafka */
        kafkaProducer.send("example-catalog-topic", requestOrder);
        orderProducer.send("orders", order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrderDto>> getOrder(@PathVariable Long userId) {
        log.info("Before retrieve orders data");

        List<ResponseOrderDto> orderList = orderService.getOrderByUserId(userId);

        log.info("After retrieved orders data");

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
}
