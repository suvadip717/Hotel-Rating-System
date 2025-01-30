package com.hrs.user.UserService.fegin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrs.user.UserService.entities.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingServices {
    @GetMapping("ratings/user/{userId}")
    public List<Rating> getUser(@PathVariable String userId);
}
