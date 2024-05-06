package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "title")
    private String title ;

    @Column(name = "description")
    private String description ;

    @Column(name = "price")
    private Integer price ;
    @Column(name = "discounted_price")
    private Integer discountedPrice ;
    @Column(name = "discounted_persent")
    private Integer discountedPersent ;


    private Integer quantity ;
    private String brand ;
    private String color ;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "product_size",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name="size_id")
//    )
//    private Set<Size> sizes ;



    @Embedded
    @ElementCollection
    @Column(name = "sizes")
    private Set<Size> sizes = new HashSet<>();

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "num_ratings")
    private Integer numRatings ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category ;

    private LocalDateTime createdAt ;




}
