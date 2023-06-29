package com.ascending3.learnrestapi3.dao.hibernate;

import com.ascending3.learnrestapi3.dao.CoachDao;
import com.ascending3.learnrestapi3.dao.CourseDao;
import com.ascending3.learnrestapi3.dao.MemberDao;
import com.ascending3.learnrestapi3.dao.impl.hibernate.CoachDaoHibernateImpl;
import com.ascending3.learnrestapi3.dao.impl.hibernate.CourseDaoHibernateImpl;
import com.ascending3.learnrestapi3.dao.impl.hibernate.MemberDaoHibernateImpl;
import com.ascending3.learnrestapi3.entity.Coach;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CoachDaoHibernateImplTest {
    private Logger logger = LoggerFactory.getLogger(CoachDaoHibernateImplTest.class);
    private static CoachDao coachDao;
    private static CourseDao courseDao;
    private static MemberDao memberDao;


    @BeforeAll
    public static void setupOnce(){
        coachDao = new CoachDaoHibernateImpl();
        courseDao = new CourseDaoHibernateImpl();
        memberDao = new MemberDaoHibernateImpl();


    }

    @AfterAll
    public static void teardownOnce(){
        coachDao = null;
        courseDao = null;
        memberDao = null;

    }

    @BeforeEach
    public void setup(){

    }


    @AfterEach
    public void teardown(){

    }

    @Test
    public void saveCoachTest(){
        Coach coach = createCoachByName("abc");
        Coach savedCoach = coachDao.save(coach);
        assertNotNull(savedCoach.getId());
        assertEquals(coach.getName(), savedCoach.getName());
        assertEquals(coach.getDescription(), savedCoach.getDescription());
    }

    private Coach createCoachByName(String name){
        Coach coach = new Coach();
        coach.setName(name);
        coach.setDescription(name + "description");
        return coach;
    }

    @Test
    public void getCoachTest(){
        List<Coach>coachList = coachDao.getCoaches();
        assertEquals(8, coachList.size());
    }


}
