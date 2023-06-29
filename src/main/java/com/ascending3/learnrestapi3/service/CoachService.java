package com.ascending3.learnrestapi3.service;

import com.ascending3.learnrestapi3.dto.CoachDto;
import com.ascending3.learnrestapi3.entity.Coach;

import java.util.List;

public interface CoachService {
    CoachDto save(CoachDto coachDto);
    CoachDto update(CoachDto coachDto);
    boolean deleteByName(String coachName);
    boolean deleteById(Long coachId);
    boolean delete(CoachDto coachDto);
    List<CoachDto> getCoaches();
    CoachDto getCoachById(Long id);
    CoachDto getCoachByName(String coachName);
    CoachDto getCoachAndMembersAndCoursesByCoachId(Long coachId);
    CoachDto getCoachAndMembersAndCoursesByCoachName(String coachName);
}
