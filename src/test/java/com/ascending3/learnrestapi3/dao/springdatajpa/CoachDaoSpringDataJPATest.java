package com.ascending3.learnrestapi3.dao.springdatajpa;


import com.ascending3.learnrestapi3.dao.CoachDao;
import com.ascending3.learnrestapi3.dao.impl.springdatajpa.CoachDaoSpringDataJpaImpl;
import com.ascending3.learnrestapi3.entity.Coach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CoachDaoSpringDataJPATest {
    private Logger logger = LoggerFactory.getLogger(CoachDaoSpringDataJPATest.class);

    @Autowired
    @Qualifier(value = "CoachDaoSpringDataJpaImpl")
    private CoachDao coachDao;


    @Test
    public void getCoachesTest(){
        List<Coach>coachList = coachDao.getCoaches();
        assertEquals(7, coachList.size());
    }

    @Test
    public void deleteCoachesTest(){
        Coach coach = createCoachByName("efg");
        Coach savedCoach = coachDao.save(coach);
        assertNotNull(savedCoach.getId(), "successfully saved Coach");
        boolean deleteSuccessfulFlag = coachDao.delete(savedCoach);
        assertTrue(deleteSuccessfulFlag);
    }

    private Coach createCoachByName(String name){
        Coach coach = new Coach();
        coach.setName(name);
        coach.setDescription(name + "description");
        return coach;
    }

}
