package com.ascending3.learnrestapi3.service.Impl;

import com.ascending3.learnrestapi3.dto.CourseDto;
import com.ascending3.learnrestapi3.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public CourseDto save(CourseDto courseDto) {
        return null;
    }

    @Override
    public CourseDto update(CourseDto courseDto) {
        return null;
    }

    @Override
    public boolean deleteByName(String courseName) {
        return false;
    }

    @Override
    public boolean deleteById(Long courseId) {
        return false;
    }

    @Override
    public boolean delete(CourseDto courseDto) {
        return false;
    }

    @Override
    public List<CourseDto> getCourses() {
        return null;
    }

    @Override
    public CourseDto getCourseById(Long id) {
        return null;
    }

    @Override
    public CourseDto getCourseByName(String courseName) {
        return null;
    }

    @Override
    public List<CourseDto> getCoursesWithAssociatedMembers() {
        return null;
    }

    @Override
    public CourseDto getCourseWithAssociatedMembersById(Long courseId) {
        return null;
    }

    @Override
    public CourseDto getCourseWithAssociatedMembersByName(String courseName) {
        return null;
    }
}
