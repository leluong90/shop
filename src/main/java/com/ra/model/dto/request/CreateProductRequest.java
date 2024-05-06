package com.ra.model.dto.request;

import com.ra.model.entity.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class CreateProductRequest {
    private String title ;
    private String description ;
    private Integer price ;
    private Integer discountedPrice ;
    private Integer discountPersent ;

    private Integer quantity ;

    private String brand ;

    private String color ;
    private Set<Size> size = new HashSet<>();
    private String imageUrl ;
    private String topLavelCategory ;
    private String secondLavelCategory ;
    private String thirdLavelCategory ;
}
