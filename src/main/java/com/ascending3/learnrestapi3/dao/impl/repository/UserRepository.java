package com.ascending3.learnrestapi3.dao.impl.repository;

import com.ascending3.learnrestapi3.entity.User;
import com.ascending3.learnrestapi3.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password) throws UserNotFoundException;

    User findByName(String name);
}
