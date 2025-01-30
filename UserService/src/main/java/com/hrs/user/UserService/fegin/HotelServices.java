package com.hrs.user.UserService.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrs.user.UserService.entities.Hotel;

@FeignClient(name = "HOTELSERVICE")
public interface HotelServices {
    @GetMapping("hotels/{hotelId}")
    public Hotel singleHotel(@PathVariable String hotelId);
}
