package com.moviecategory.moviecategory.service;

import com.moviecategory.moviecategory.dto.MovieDto;

import java.util.List;

public interface MovieCategoryService {
     List<MovieDto> getMoviesByCategory(String category);
}
