package com.moviecategory.moviecategory.dto;


import lombok.Data;

@Data
public class MovieInfoDto {
    private Long id;
    private String name;
    private String category;
    private String description;
}
