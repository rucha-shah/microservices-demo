package io.javabrains.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatalogservice.module.CatalogItem;
import io.javabrains.moviecatalogservice.module.Movie;
import io.javabrains.moviecatalogservice.module.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogService {
    
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogItem(@PathVariable("userId")String userId){

        RestTemplate restTemplate=new RestTemplate();
        //Movie movie=restTemplate.getForObject("http://localhost:8082/movies/foo",Movie.class);
        List<Rating> ratings=Arrays.asList(
            new Rating("1234",4),
            new Rating("5678",3)
        );

        return ratings.stream().map(rating -> 
        {
            Movie movie=restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);
            return new CatalogItem(movie.getName(), "Super-Hero Sci-Fi", rating.getRating());
        })
        .collect(Collectors.toList());
        // return Collections.singletonList(
        //     new CatalogItem("Avengers", "Super-Hero Sci-Fi", 4)
        // );
    }
}