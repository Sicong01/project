package com.ascending3.learnrestapi3.dao.impl.repository;

import com.ascending3.learnrestapi3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
