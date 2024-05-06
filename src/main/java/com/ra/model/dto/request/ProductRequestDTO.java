package com.ra.model.dto.request;

import com.ra.model.entity.Category;
import com.ra.model.entity.Rating;
import com.ra.model.entity.Review;
import com.ra.model.entity.Size;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ProductRequestDTO {

    private String title ;
    private String description ;
    private Integer price ;
    private Integer discountedPrice ;
    private Integer discountedPercent ;
    private Integer quantity ;
    private String brand ;
    private String color ;

    public Set<Size> sizes = new HashSet<>();


    private String imageUrl;

    private String topLavelCategory ;

    private String secondLavelCategory ;
    private String thirdLavelCategory ;



}
