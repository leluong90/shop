package com.ra.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequestDTO {
    private String email ;
    private String password ;
}
