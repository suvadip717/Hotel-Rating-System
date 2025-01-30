package com.hrs.user.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrs.user.UserService.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
