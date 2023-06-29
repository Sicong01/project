package com.ascending3.learnrestapi3.dao;

import com.ascending3.learnrestapi3.entity.Course;

import java.util.List;

public interface CourseDao {
    Course save(Course course);
    Course update(Course course);
    boolean deleteByName(String courseName);
    boolean deleteById(Long courseId);
    boolean delete(Course course);
    List<Course> getCourses();
    Course getCourseById(Long id);
    Course getCourseByName(String courseName);
    List<Course> getCoursesWithAssociatedMembers();
    Course getCourseWithAssociatedMembersById(Long courseId);
    Course getCourseWithAssociatedMembersByName(String courseName);
}
