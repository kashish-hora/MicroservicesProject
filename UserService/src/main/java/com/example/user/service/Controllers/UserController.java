package com.example.user.service.Controllers;

import com.example.user.service.entities.User;
import com.example.user.service.impl.UserServiceImpl;
import com.example.user.service.service.Userservice;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Userservice userservice;

    private Logger logger= LoggerFactory.getLogger(UserController.class);
    //create
    @PostMapping
    public ResponseEntity<User> createuser(@RequestBody User user){


        User user1=userservice.saveuser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retrycount=1;
    //get single user
    @GetMapping("/{userId}")
   // @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod ="ratingHotelFallback")
    //@Retry(name="ratingHotelService",fallbackMethod ="ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod ="ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Retry count:{}",retrycount);
        retrycount++;
       User user= userservice.getUser(userId);
        return ResponseEntity.ok(user);
    }

   // creating fall back method for circuitbreak



    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
       // logger.info("Fallback is executed because service is down:"+ex.getMessage());
     User user=   User.builder()
                .email("dummy@gail.com")
                .name("dummy").about("This user is created dummy because service is down")
                .userId("13456")
                .build();
                return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
       List< User > user= userservice.getAllUser();
        return ResponseEntity.ok(user);
    }


}
