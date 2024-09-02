package com.rahul.demoApp.Services;

import com.rahul.demoApp.Entity.User;
import com.rahul.demoApp.Payload.UserDto;
import com.rahul.demoApp.Payload.UserResponse;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    UserResponse searchUser(String firstName, Integer pageNumber, Integer pageSize);
}
