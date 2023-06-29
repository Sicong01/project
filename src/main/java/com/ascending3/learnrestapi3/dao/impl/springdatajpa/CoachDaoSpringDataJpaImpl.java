package com.ascending3.learnrestapi3.dao.impl.springdatajpa;

import com.ascending3.learnrestapi3.dao.CoachDao;
import com.ascending3.learnrestapi3.dao.impl.hibernate.CoachDaoHibernateImpl;
import com.ascending3.learnrestapi3.dao.impl.repository.CoachRepository;
import com.ascending3.learnrestapi3.entity.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("CoachDaoSpringDataJpaImpl")
public class CoachDaoSpringDataJpaImpl implements CoachDao {
    private Logger logger = LoggerFactory.getLogger(CoachDaoSpringDataJpaImpl.class);

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Coach save(Coach coach) {
        Coach savedCoach = coachRepository.save(coach);
        return savedCoach;
    }

    @Override
    public Coach update(Coach coach) {
        Coach updatedCoach = coachRepository.save(coach);
        return updatedCoach;
    }

    @Override
    public boolean deleteByName(String coachName) {
//        return coachRepository.deleteByName(coachName) > 0;

        boolean successFlag = false;
        try {
            int deleteResult = coachRepository.deleteByName(coachName);
            if (deleteResult > 0)
                successFlag = true;
        }catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteByName with coachName={}, error={}", coachName, iae.getMessage());
        }catch (OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteByName with coachName={}, error={}", coachName, olfe.getMessage());
        }
        return successFlag;
    }

    @Override
    public boolean deleteById(Long coachId) {
        boolean successFlag = false;
        try {
            Optional<Coach> optionalCoach = coachRepository.findById(coachId);
            if (optionalCoach.isPresent()) {
                coachRepository.deleteById(coachId);
                successFlag = true;
            }
        }catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteById with coachId={}, error={}", coachId, iae.getMessage());
        }catch (OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteById with coachId={}, error={}", coachId, olfe.getMessage());
        }
        return successFlag;
    }
    
    @Override
    public boolean delete(Coach coach) {
        boolean successFlag = false;
        if (coach != null){
            successFlag = deletingCoach(coach);
        }
        return successFlag;
//        try {
//            Optional<Coach> optionalCoach = coachRepository.findById(coach.getId());
//            if (optionalCoach.isPresent()) {
//                coachRepository.delete(coach);
//                successFlag = true;
//            }
//        }catch (IllegalArgumentException iae){
//            logger.error("caught IllegalArgumentException when trying deleteById with coachId={}, error={}", iae.getMessage());
//        }catch (OptimisticLockingFailureException olfe){
//            logger.error("caught OptimisticLockingFailureException when trying deleteById with coachId={}, error={}", olfe.getMessage());
//        }
//        return successFlag;
    }

    private boolean deletingCoach(Coach coach) {
        boolean deleteFlag = false;
        try {
            Optional<Coach> optionalCoach = coachRepository.findById(coach.getId());
            if (optionalCoach.isPresent()) {
                coachRepository.delete(coach);
                deleteFlag = true;
            }
        }catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying delete coach, error={}", iae.getMessage());
        }catch (OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying delete coach, error={}", olfe.getMessage());
        }
        return deleteFlag;
    }


    @Override
    public List<Coach> getCoaches() {
        List<Coach> coachList = coachRepository.findAll();
        if(coachList == null)
            coachList = new ArrayList<Coach>();
        return coachList;
    }

    @Override
    public Coach getCoachById(Long id) {
        Coach coach = null;
        Optional<Coach> coachOptional = coachRepository.findById(id);
        if(coachOptional.isPresent())
            coach = coachOptional.get();
        return coach;

    }

    @Override
    public Coach getCoachByName(String coachName) {
        return coachRepository.findByName(coachName);
    }

    @Override
    public Coach getCoachAndMembersAndCoursesByCoachId(Long coachId) {
        return null;
    }

    @Override
    public Coach getCoachAndMembersAndCoursesByCoachName(String coachName) {
        return null;
    }
}
