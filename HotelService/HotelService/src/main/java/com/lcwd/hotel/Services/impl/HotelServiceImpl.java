package com.lcwd.hotel.Services.impl;

import com.lcwd.hotel.Services.HotelService;
import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.repostories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelrepo;
    @Override
    public Hotel create(Hotel hotel) {

        String hotelid= UUID.randomUUID().toString();
        hotel.setId(hotelid);
        return hotelrepo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelrepo.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id not found!!"));
    }
    }

