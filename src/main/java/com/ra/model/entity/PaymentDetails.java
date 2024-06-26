package com.ra.model.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class PaymentDetails {

    private String paymentMethod ;
    private String status ;
    private String paymentId ;
    private String razorpayPaymentLinkId ;
    private String razorpayPaymentLinkReferenceId ;
    private String razorpayPaymentLinkStatus ;
    private String razorpayPaymentId ;


}
