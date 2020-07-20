package io.javabrains.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.moviecatalogservice.module.CatalogItem;
import io.javabrains.moviecatalogservice.module.Movie;
import io.javabrains.moviecatalogservice.module.Rating;
import io.javabrains.moviecatalogservice.module.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogItem(@PathVariable("userId")String userId){

        UserRating ratings=restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId, UserRating.class);


        return ratings.getUserRating().stream().map(rating -> 
        {
          Movie movie=restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);
           return new CatalogItem(movie.getName(), "Super-Hero Sci-Fi", rating.getRating());
        })
        .collect(Collectors.toList());

    }
}

//WebClient way instead of getForObject
//WebClient for asynchronous calls
//getForObject synchronous calls
 /*   Movie movie=webClientBuilder.build()
                  .get()
                  .uri("http://localhost:8082/movies/"+rating.getMovieId())
                  .retrieve()
                  .bodyToMono(Movie.class)
                  */

                  //.block() can be added to make above call synchronous
       