package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "order_id")
    private String orderId ;

    @ManyToOne
    private User user ;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate ;

    private LocalDateTime deliveryDate ;

    @OneToOne
    private Address shippingAddress ;

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails() ;

    private Double totalPrice ;

    private Integer totalDiscountedPrice ;

    private Integer discounted ;

    private String orderStatus ;

    private Integer totalItem ;

    private LocalDateTime createAt ;

}
