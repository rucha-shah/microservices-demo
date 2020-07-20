package io.javabrains.ratingdataservice.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingdataservice.module.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId")String movieId){
        return new Rating(movieId,3);
    }
}