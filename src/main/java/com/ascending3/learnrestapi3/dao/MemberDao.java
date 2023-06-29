package com.ascending3.learnrestapi3.dao;


import com.ascending3.learnrestapi3.entity.Course;
import com.ascending3.learnrestapi3.entity.Member;

import java.util.List;

public interface MemberDao {
    Member save(Member member, Long coachId);
    Member update(Member member);
    boolean deleteByLoginName(String loginName);
    boolean deleteById(Long memberId);
    boolean delete(Member member);
    List<Member> getMembers();
    Member getMemberById(Long id);
    Member getMemberByLoginName(String loginName);

    List<Member> getMembersByCoachId (Long coachId);
    List<Course> getAssociatedCoursesByMemberId(Long memberId);
    Member getMemberWithAssociatedCoursesByMemberId(Long memberId);

}
