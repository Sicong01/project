package com.ascending3.learnrestapi3.dao;

import com.ascending3.learnrestapi3.entity.Coach;

import java.util.List;

public interface CoachDao {
    Coach save(Coach coach);
    Coach update(Coach coach);
    boolean deleteByName(String coachName);
    boolean deleteById(Long coachId);
    boolean delete(Coach coach);
    List<Coach> getCoaches();
    Coach getCoachById(Long id);
    Coach getCoachByName(String coachName);
    Coach getCoachAndMembersAndCoursesByCoachId(Long coachId);
    Coach getCoachAndMembersAndCoursesByCoachName(String coachName);
}
