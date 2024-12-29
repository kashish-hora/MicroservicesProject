package com.lcwd.rating.RatingService.Service.impl;

import com.lcwd.rating.RatingService.Service.RatingService;
import com.lcwd.rating.RatingService.entities.Rating;
import com.lcwd.rating.RatingService.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingrepo;
    @Override
    public Rating create(Rating rating) {
        return ratingrepo.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
         return ratingrepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingrepo.findByUserId( userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingrepo.findByHotelId( hotelId);
    }
}
