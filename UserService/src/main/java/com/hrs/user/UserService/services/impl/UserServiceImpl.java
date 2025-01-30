package com.hrs.user.UserService.services.impl;

import java.util.List;
import java.util.UUID;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

import com.hrs.user.UserService.entities.Hotel;
import com.hrs.user.UserService.entities.Rating;
import com.hrs.user.UserService.entities.User;
import com.hrs.user.UserService.exceptions.ResourceNotFoundException;
import com.hrs.user.UserService.fegin.HotelServices;
import com.hrs.user.UserService.fegin.RatingServices;
import com.hrs.user.UserService.repositories.UserRepository;
import com.hrs.user.UserService.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingServices ratingServices;

    @Autowired
    private HotelServices hotelServices;

    // @Autowired
    // private RestTemplate restTemplate;

    // private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User savaUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            List<Rating> ratingsOfUser = ratingServices.getUser(user.getUserId());
            for (Rating rating : ratingsOfUser) {
                Hotel hotel = hotelServices.singleHotel(rating.getHotelId());
                rating.setHotel(hotel);
            }
            user.setRatings(ratingsOfUser);
        }
        return users;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with given id is not found in server " + userId));
        // ArrayList<Rating> ratingsOfUser = restTemplate.getForObject(
        // "http://localhost:8083/ratings/user/" + user.getUserId(), ArrayList.class);
        // logger.info("", ratingsOfUser);
        List<Rating> ratingsOfUser = ratingServices.getUser(userId);
        for (Rating rating : ratingsOfUser) {
            Hotel hotel = hotelServices.singleHotel(rating.getHotelId());
            rating.setHotel(hotel);
        }
        user.setRatings(ratingsOfUser);
        return user;
    }

    @Override
    public String deleteUser(String userId) {
        userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with given id is not found in server " + userId));
        userRepository.deleteById(userId);
        return "User delete successfully";
    }

    @Override
    public User updateUser(String userId, User newUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with given id is not found in server " + userId));
        user.setAbout(newUser.getAbout());
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        return userRepository.save(user);
    }

}
