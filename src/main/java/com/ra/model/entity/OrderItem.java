package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @JsonIgnore
    @ManyToOne
    private Order order ;

    @ManyToOne
    private Product product ;

    private String size ;

    private Integer quantity ;

    private Integer price ;

    private Integer discountedPrice ;

    private Long userId ;

    private LocalDateTime  deliveryDate;


}
