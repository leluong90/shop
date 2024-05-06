package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true)
    private String userName ;
    private String firstName ;
    private String lastName ;
//    private String fullName ;
    private String email;
    private String password ;
    private String mobile ;
    private Boolean status ;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles ;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();


//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_payment_information",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name="payment_information_id")
//    )
//    private List<PaymentInformation> paymentInformations ;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "payment_information" , joinColumns = @JoinColumn(name = "user_id"))
    private List<PaymentInformation> paymentInformation = new ArrayList<>() ;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_rating",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name="payment_information_id")
//    )
//    private List<Rating> rating ;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    private LocalDateTime  createdAt ;



}
