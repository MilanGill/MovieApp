package com.moviecategory.moviecategory.dto;

import lombok.Data;

@Data
public class MovieDto {
    private String name;
    private String category;
    private String description;
    private Double rating;
}
