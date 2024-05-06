package com.ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddItemRequest {
    private Long id ;

    private String size ;

    private Integer quantity ;

    private Integer price ;




}
