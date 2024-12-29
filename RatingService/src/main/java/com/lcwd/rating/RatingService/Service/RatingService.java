package com.lcwd.rating.RatingService.Service;

import com.lcwd.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);


    //get all ratings
    List<Rating> getRatings();

    //get all by userid
    List<Rating> getRatingByUserId(String userId);


    //get all by hotelid
    List<Rating> getRatingByHotelId(String hotelId);

}
