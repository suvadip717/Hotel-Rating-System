package com.hrs.user.UserService.entities;

import lombok.Data;

@Data
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedBack;
    private Hotel hotel;
}
