package com.ascending3.learnrestapi3.service;

import com.ascending3.learnrestapi3.dto.UserDto;
//import com.ascendingdc.training.project2020.entity.User;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto getUserByEmail(String email);

    UserDto getUserById(Long userid);

    UserDto getUserByCredentials(String email, String password);

    List<UserDto> getAllUsers();


}
