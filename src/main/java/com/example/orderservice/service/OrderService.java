package com.example.orderservice.service;

import com.example.orderservice.domain.Orders;
import com.example.orderservice.dto.RequestOrderDto;
import com.example.orderservice.dto.ResponseOrderDto;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public ResponseOrderDto createOrder(RequestOrderDto requestOrder, Long userId) {
        Orders orders = Orders.builder()
                .productId(requestOrder.getProductId())
                .qty(requestOrder.getQty())
                .unitPrice(requestOrder.getUnitPrice())
                .userId(userId)
                .build();

        orderRepository.save(orders);

        return new ResponseOrderDto(orders);
    }

    public ResponseOrderDto getOrderByOrderId(String orderId) {
        Orders orders = orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() -> new IllegalArgumentException("잘못 된 주문번호입니다."));

        return new ResponseOrderDto(orders);
    }

    public List<ResponseOrderDto> getOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(ResponseOrderDto::new)
                .toList();
    }

}
