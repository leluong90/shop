package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



public class PaymentInformation {
    @Column(name = "cardholder_name")
    private String cardholderName ;
    @Column(name = "card_number")
    private String cardNumber ;
    @Column(name = "expiration_date")
    private LocalDate expirationDate ;
    @Column(name = "cvv")
    private String cvv ;



}
