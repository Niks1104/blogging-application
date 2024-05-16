package com.blogapp.services;

import com.blogapp.payloads.UserDto;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateuser(UserDto user, Integer userId);
    UserDto getUserByUserId(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
