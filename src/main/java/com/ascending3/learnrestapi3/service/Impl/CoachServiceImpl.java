package com.ascending3.learnrestapi3.service.Impl;

import com.ascending3.learnrestapi3.dao.CoachDao;
import com.ascending3.learnrestapi3.dao.MemberDao;
import com.ascending3.learnrestapi3.dto.CoachDto;
import com.ascending3.learnrestapi3.entity.Coach;
import com.ascending3.learnrestapi3.exception.ItemNotFoundException;
import com.ascending3.learnrestapi3.service.CoachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    private Logger logger = LoggerFactory.getLogger(CoachServiceImpl.class);

    @Autowired
    @Qualifier("CoachDaoSpringDataJpaImpl")
    private CoachDao coachDao;

    @Autowired
    @Qualifier("MemberDaoSpringDataJpaImpl")
    private MemberDao memberDao;

    @Override
    public CoachDto save(CoachDto coachDto) {
        Coach coach = coachDto.convertCoachDtoToCoach();
        Coach savedCoach = coachDao.save(coach);
        CoachDto savedCoachDto = savedCoach.convertCoachToCoachDto();
        return savedCoachDto;
    }

    @Override
    public CoachDto update(CoachDto coachDto) {
        Coach coach = coachDto.convertCoachDtoToCoach();
        Coach updatedCoach = coachDao.update(coach);
        CoachDto updatedCoachDto = updatedCoach.convertCoachToCoachDto();
        return updatedCoachDto;
    }

    @Override
    public boolean deleteByName(String coachName) {
        boolean deleteResult = coachDao.deleteByName(coachName);
        return deleteResult;
    }

    @Override
    public boolean deleteById(Long coachId) {
        boolean deleteResult = coachDao.deleteById(coachId);
        logger.debug(" == within method CoachService.deleteById(...), coachId={}, deleteResult={}", coachId,deleteResult);
        return deleteResult;
    }

    @Override
    public boolean delete(CoachDto coachDto) {
        Coach coach = coachDto.convertCoachDtoToCoach();
        boolean deleteResult = coachDao.delete(coach);
        return deleteResult;
    }

    @Override
    public List<CoachDto> getCoaches() {
        List<Coach> coachList = coachDao.getCoaches();
        List<CoachDto> coachDtoList = new ArrayList<>();

        for (Coach coach : coachList){
            CoachDto coachDto = coach.convertCoachToCoachDto();
            coachDtoList.add(coachDto);
        }
        return coachDtoList;
    }

    private List<CoachDto> getCoachDtoListFromCoachList(List<Coach> coachList) {
        List<CoachDto> coachDtoList = new ArrayList<>();
        for (Coach coach : coachList){
            CoachDto coachDto = coach.convertCoachToCoachDto();
            coachDtoList.add(coachDto);
        }
        return coachDtoList;
    }

    @Override
    public CoachDto getCoachById(Long id) {
        CoachDto coachDto = null;
        Coach coach = coachDao.getCoachById(id);
        if(coach != null) {
            coachDto = coach.convertCoachToCoachDto();
        }else{
            throw new ItemNotFoundException(String.format("Could not find Coach with id = " + id));
        }
        return coachDto;
    }

    @Override
    public CoachDto getCoachByName(String coachName) {
        Coach retrievedCoach = coachDao.getCoachByName(coachName);
        CoachDto retrievedCoachDto = retrievedCoach.convertCoachToCoachDto();
        return retrievedCoachDto;
    }

    @Override
    public CoachDto getCoachAndMembersAndCoursesByCoachId(Long coachId) {
        Coach retrievedCoach = coachDao.getCoachAndMembersAndCoursesByCoachId(coachId);
        CoachDto retrievedCoachDto = retrievedCoach.convertCoachToCoachDto();
        return retrievedCoachDto;
    }

    @Override
    public CoachDto getCoachAndMembersAndCoursesByCoachName(String coachName) {
        Coach retrievedCoach = coachDao.getCoachAndMembersAndCoursesByCoachName(coachName);
        CoachDto retrievedCoachDto = retrievedCoach.convertCoachToCoachDto();
        return retrievedCoachDto;
    }
}
