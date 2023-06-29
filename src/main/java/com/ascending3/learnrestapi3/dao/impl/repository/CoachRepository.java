package com.ascending3.learnrestapi3.dao.impl.repository;

import com.ascending3.learnrestapi3.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    int deleteByName(String name);

    Coach findByName(String name);

//    @Query("SELECT distinct c from Coach as c left join fetch c.members as member left join fetch member.courses where c.id = :id")
//    Coach findCoachWithMembersAndCoursesByCoachId(@Param(value = "id") Long coachId);

//    @Query("SELECT distinct c FROM Coach as c left join fetch c.members as member left join fetch member.courses")
//    List<Coach> findAllCoachesWithMembersAndCourses();

//    @Query("FROM Coach as c where c.id = :id")
//    Coach findCoachByCoachId(@Param(value = "id") Long coachId);
}
