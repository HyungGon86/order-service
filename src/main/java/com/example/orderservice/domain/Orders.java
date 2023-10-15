package com.example.orderservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Orders {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String productId;
    private int qty;
    private int unitPrice;
    private int totalPrice;
    private Long userId;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createAt;

    @Builder
    public Orders(String productId, int qty, int unitPrice, Long userId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.userId = userId;
        setTotalPrice(qty, unitPrice);
    }

    // 총 가격 구하기
    public void setTotalPrice(int qty, int unitPrice) {
        this.totalPrice = qty * unitPrice;
    }
}
