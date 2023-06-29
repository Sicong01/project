package com.ascending3.learnrestapi3.dao.impl.springdatajpa;

import com.ascending3.learnrestapi3.dao.CourseDao;
import com.ascending3.learnrestapi3.dao.impl.repository.CoachRepository;
import com.ascending3.learnrestapi3.dao.impl.repository.CourseRepository;
import com.ascending3.learnrestapi3.entity.Coach;
import com.ascending3.learnrestapi3.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("CourseDaoSpringDataJpaImpl")

public class CourseDaoSpringDataJpaImpl implements CourseDao {

    private Logger logger = LoggerFactory.getLogger(CourseDaoSpringDataJpaImpl.class);


    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        Course savedCourse = courseRepository.save(course);
        return savedCourse;
    }

    @Override
    public Course update(Course course) {
        Course updatedCourse = courseRepository.save(course);
        return updatedCourse;
    }

    @Override
    public boolean deleteByName(String courseName) {
        return false;
    }

    @Override
    public boolean deleteById(Long courseId) {
        boolean successFlag = false;
        try {
            courseRepository.deleteById(courseId);
            successFlag = true;
        }catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying deleteById with courseId={}, error={}", courseId, iae.getMessage());
        }catch (OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying deleteById with courseId={}, error={}", courseId, olfe.getMessage());
        }
        return successFlag;
    }

    @Override
    public boolean delete(Course course) {
        boolean successFlag = false;
        try {
            courseRepository.delete(course);
            successFlag = true;
        }catch (IllegalArgumentException iae){
            logger.error("caught IllegalArgumentException when trying delete course, error={}", course, iae.getMessage());
        }catch (OptimisticLockingFailureException olfe){
            logger.error("caught OptimisticLockingFailureException when trying delete course, error={}", course, olfe.getMessage());
        }
        return successFlag;
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        Course course = null;
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isPresent())
            course = courseOptional.get();
        return course;

    }

    @Override
    public Course getCourseByName(String courseName) {
        return null;
    }

    @Override
    public List<Course> getCoursesWithAssociatedMembers() {
        return null;
    }

    @Override
    public Course getCourseWithAssociatedMembersById(Long courseId) {
        return null;
    }

    @Override
    public Course getCourseWithAssociatedMembersByName(String courseName) {
        return null;
    }
}
