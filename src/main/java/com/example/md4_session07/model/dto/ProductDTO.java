package com.example.md4_session07.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDTO {
    private String name ;
    private String description ;
    private int price;
    private int stock ;
}
