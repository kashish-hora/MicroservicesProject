package com.lcwd.rating.RatingService.Contollers;

import com.lcwd.rating.RatingService.Service.RatingService;
import com.lcwd.rating.RatingService.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    //create rating
    @Autowired
    private RatingService ratingservice;

@PostMapping
    public ResponseEntity <Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingservice.create(rating));
    }
    // GET ALL users ratings

    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.ok(ratingservice.getRatings());
    }
@GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId( @PathVariable String userId){
        return ResponseEntity.ok(ratingservice.getRatingByUserId(userId));
    }
    //get all ratings of hotels

@GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId( @PathVariable String hotelId){
        return ResponseEntity.ok(ratingservice.getRatingByHotelId(hotelId));
    }



}
