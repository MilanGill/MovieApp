package com.moviecategory.moviecategory.service.Impl;

import com.moviecategory.moviecategory.dto.MovieDto;
import com.moviecategory.moviecategory.dto.MovieInfoDto;
import com.moviecategory.moviecategory.dto.MovieRatingsDto;
import com.moviecategory.moviecategory.service.MovieCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieCategoryServiceImpl implements MovieCategoryService {
    public static final String MOVIE_INFO_SERVICE_URL = "http://localhost:8081/movie-info";
    public static final String MOVIE_RATING_URL = "http://localhost:8082/movie-ratings";

    private final RestTemplate restTemplate;
    @Override
    public List<MovieDto> getMoviesByCategory(String category) {
         ResponseEntity<List<MovieInfoDto>> movieInfoServiceResponse = restTemplate.exchange(
                MOVIE_INFO_SERVICE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieInfoDto>>() {
                }
        );

        ResponseEntity<List<MovieRatingsDto>> movieRatingResponse = restTemplate.exchange(
                MOVIE_RATING_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieRatingsDto>>() {
                }
        );

         List<MovieInfoDto> movieInfoList = movieInfoServiceResponse.getBody();
         List<MovieRatingsDto> movieRatingsDtoList = movieRatingResponse.getBody();

         List<MovieDto> movieDtos = new LinkedList<>();
        assert movieInfoList != null;
        for (MovieInfoDto movieInfoDto : movieInfoList){
            if(category.equals(movieInfoDto.getCategory())) {
                MovieDto movieDto = new MovieDto();

                movieDto.setName(movieInfoDto.getName());
                movieDto.setCategory(movieInfoDto.getCategory());
                movieDto.setDescription(movieInfoDto.getDescription());

                movieDtos.add(movieDto);
            }
         }

        for (MovieDto movieDto : movieDtos ) {
            String movieDtoname = movieDto.getName();

            for (MovieRatingsDto movieRatingsDto : movieRatingsDtoList) {
                if (movieDtoname.equals(movieRatingsDto.getName())) {
                    movieDto.setRating(movieRatingsDto.getRating());
                }
            }
        }
        return movieDtos;
    }
}
