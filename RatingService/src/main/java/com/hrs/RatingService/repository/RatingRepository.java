package com.hrs.RatingService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hrs.RatingService.entities.Rating;
import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    // custom finder method
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
