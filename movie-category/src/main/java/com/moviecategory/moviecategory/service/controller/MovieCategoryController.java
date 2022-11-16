package com.moviecategory.moviecategory.service.controller;

import com.moviecategory.moviecategory.dto.MovieDto;
import com.moviecategory.moviecategory.service.MovieCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.Reque "stParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieCategoryController {
    private final MovieCategoryService movieCategoryService;

    @GetMapping()
    public List<MovieDto> getMovieByCategory(@RequestParam(name = "category") String category) {
        return movieCategoryService.getMoviesByCategory(category);
    }
}
