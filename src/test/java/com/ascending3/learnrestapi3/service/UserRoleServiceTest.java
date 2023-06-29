package com.ascending3.learnrestapi3.service;

import com.ascending3.learnrestapi3.dao.CoachDao;
import com.ascending3.learnrestapi3.dao.CourseDao;
import com.ascending3.learnrestapi3.dao.MemberDao;
import com.ascending3.learnrestapi3.dao.UserDao;
import com.ascending3.learnrestapi3.dto.UserDto;
import com.ascending3.learnrestapi3.service.Impl.CoachServiceImpl;
import com.ascending3.learnrestapi3.service.Impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRoleServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());


    @Mock
    private UserDao mockUserDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void initEach(){
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    public void findAllUsersTest(){
        List<UserDto> userDtoList = userService.getAllUsers();
        displayUsers(userDtoList);
    }

    @Test
    public void encryptPassword(){
        String encryptPasswordFor123456789 = DigestUtils.md5Hex("123456789");
        logger.info("Hashed Password = {}", encryptPasswordFor123456789);
        String expectedEncryptPassword123456789 = "25f9e794323b453885f5181f1b624d0b";
        assertEquals(expectedEncryptPassword123456789, encryptPasswordFor123456789, "the encrypt password for 123456789 should be equal");
    }

    private void displayUsers(List<UserDto> userDtoList) {
        int index = 1;
        for (UserDto userDto : userDtoList){
            displayUser(index++, userDto);
        }
    }

    private void displayUser(int i, UserDto userDto) {
        logger.info("======= User No.{} =======", i);
        logger.info("\t user.id={}", userDto.getId());
        logger.info("\t user.name={}", userDto.getName());
        logger.info("\t user.firstName={}", userDto.getFirstName());
        logger.info("\t user.lastName={}", userDto.getLastName());
        logger.info("\t user.email={}", userDto.getEmail());
    }
}
