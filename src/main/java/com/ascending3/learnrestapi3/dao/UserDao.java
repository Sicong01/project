package com.ascending3.learnrestapi3.dao;

import com.ascending3.learnrestapi3.entity.Role;
import com.ascending3.learnrestapi3.entity.User;
import com.ascending3.learnrestapi3.exception.UserNotFoundException;

import java.util.List;

public interface UserDao {
    User save(User user);
    User getUserByEmail(String email);
    User getUserById(Long id);
    User getUserByCredentials(String email, String password) throws UserNotFoundException;
    User addRole(User user, Role role);
    boolean delete(User user);
    List<User> findAllUsers();
    User getUserByName(String username);
}
