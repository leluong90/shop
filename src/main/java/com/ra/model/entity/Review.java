package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String review ;

    @ManyToOne
    @JoinColumn(name= "product_id")
    @JsonIgnore
    private Product product ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    private LocalDateTime createdAt ;
}
