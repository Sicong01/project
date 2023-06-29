package com.ascending3.learnrestapi3.service;

import com.ascending3.learnrestapi3.dto.CourseDto;
import com.ascending3.learnrestapi3.entity.Course;

import java.util.List;

public interface CourseService {
    CourseDto save(CourseDto courseDto);
    CourseDto update(CourseDto courseDto);
    boolean deleteByName(String courseName);
    boolean deleteById(Long courseId);
    boolean delete(CourseDto courseDto);
    List<CourseDto> getCourses();
    CourseDto getCourseById(Long id);
    CourseDto getCourseByName(String courseName);
    List<CourseDto> getCoursesWithAssociatedMembers();
    CourseDto getCourseWithAssociatedMembersById(Long courseId);
    CourseDto getCourseWithAssociatedMembersByName(String courseName);
}
