package com.hrs.RatingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.RatingService.entities.Rating;
import com.hrs.RatingService.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    // create rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    // get all
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    // get all hotel
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

    // get all User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getUser(@PathVariable String userId) {
        // System.out.println("Calling me");
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
}
