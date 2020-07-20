package io.javabrains.ratingdataservice.resource;

import java.util.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingdataservice.module.Rating;
import io.javabrains.ratingdataservice.module.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId")String movieId){
        return new Rating(movieId,3);
    }
    
    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId")String userId){
        List<Rating> ratings=Arrays.asList(
            new Rating("1234",4),
            new Rating("5678",3)
        );
        UserRating userRating=new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
    
}