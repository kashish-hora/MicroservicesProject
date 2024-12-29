package com.example.user.service.impl;

import com.example.user.service.entities.Hotel;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.external.services.HotelService;
import com.example.user.service.repositories.Userrepository;
import com.example.user.service.service.Userservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements Userservice {
    @Autowired
    private Userrepository userrepo;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;


    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveuser(User user) {
        //generate unique userid

        String randomuserId= UUID.randomUUID().toString();
        user.setUserId(randomuserId);
       return  userrepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userrepo.findAll();
    }

    @Override

    public User getUser(String userId) {
        //get user from database with help of user repository
        User user=userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id is not found o server:"+userId));
        //fetch rating of above user from rating service
     Rating[] ratingsofuser=   restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);

     logger.info("{}",ratingsofuser);
     List<Rating> ratings=Arrays.stream(ratingsofuser).toList();
        List<Rating> ratinglist=ratings.stream().map(rating -> {
            //api call to hotel service to get hotel
//            ResponseEntity<Hotel> forentity=restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel=forentity.getBody();
//            logger.info("response status code :{}",forentity.getStatusCode());

            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            //set the hotel to rating
            rating.setHotel(hotel);

            //return the rating
            return rating;


        }).collect(Collectors.toList());
        user.setRatings(ratinglist);
             return user;

    }

}
