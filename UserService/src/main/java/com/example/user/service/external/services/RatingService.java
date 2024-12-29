package com.example.user.service.external.services;

import com.example.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
@Service
@FeignClient(name="RATINGSERVICE")
public interface RatingService {

    //get

    //post
    @PostMapping("/ratings")
    Rating createRating(Rating values);

    //put
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    //delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRatings(@PathVariable String ratingId);

}
