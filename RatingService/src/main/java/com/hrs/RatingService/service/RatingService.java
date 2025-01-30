package com.hrs.RatingService.service;

import java.util.List;

import com.hrs.RatingService.entities.Rating;

public interface RatingService {
    // create
    Rating create(Rating rating);

    // get all rating
    List<Rating> getRatings();

    // get all by userId
    List<Rating> getRatingByUserId(String userId);

    // get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);

}
