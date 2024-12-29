package com.lcwd.hotel.Controllers;

import com.lcwd.hotel.Services.HotelService;
import com.lcwd.hotel.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelservice;

    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {


        return ResponseEntity.status(HttpStatus.CREATED).body(hotelservice.create(hotel));


    }

    @GetMapping("/{hotelid}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelid) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelservice.get(hotelid));


    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotel() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelservice.getAll());


    }
}