package com.ascending3.learnrestapi3.dao.springdatajpa;

import com.ascending3.learnrestapi3.dao.CoachDao;
import com.ascending3.learnrestapi3.dao.UserDao;
import com.ascending3.learnrestapi3.entity.Role;
import com.ascending3.learnrestapi3.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class UserDaoSpringDataJPATest {
    private Logger logger = LoggerFactory.getLogger(CoachDaoSpringDataJPATest.class);

    @Autowired
    @Qualifier(value = "userSpringDataJPADao")
    private UserDao userDao;

    @Test
    public void testFindAllUsers(){
        List<User> userList = userDao.findAllUsers();
        displayUserList(userList);
    }

    @Test
    public void testFindUserByEmailAndPassword(){
        String email = "dwang@training.ascendingdc.com";
        String password = "25f9e794323b453885f5181f1b624d0b";
        User user = userDao.getUserByCredentials(email, password);
        logger.info("=== user={}", user);
        displayRoleList(user.getRoles());
    }

    private void displayUserList(List<User> userList) {
        logger.info("=== the total number of retrieved Users = {}", userList.size());
        int index = 1;
        for (User user : userList){
            logger.info("=== user No.{}, user={}", index++, user);
            displayRoleList(user.getRoles());
        }
    }

    private void displayRoleList(Set<Role> roles) {
        logger.info("=== the total number of retrieved Roles = {}", roles.size());
        int index = 1;
        for (Role role : roles){
            logger.info("=== Role No.{}, Role={}", index++, role);
        }
    }


}
