package com.hrs.user.UserService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.user.UserService.entities.User;
import com.hrs.user.UserService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User creatUser = userService.savaUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(creatUser);
        // return new ResponseEntity<>(creatUser, HttpStatus.CREATED);
    }

    int retryCount = 1;

    // single user
    @GetMapping("/{userId}")
    // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod =
    // "ratingHotelFallBack")
    // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        logger.info("Retry count{}: ", retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // Creating fall back method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallBack(@PathVariable String userId, Exception ex) {
        User user = new User();
        logger.info("Fallback is executed because service is down: ", ex.getMessage());
        user.setName("dummy");
        user.setEmail("dummy123@gmail.com");
        user.setAbout("This is a dummy user");
        user.setUserId("1234");
        return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
    }

    // all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        String message = userService.deleteUser(userId);
        return ResponseEntity.ok(message);
    }

    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        User newUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

}
