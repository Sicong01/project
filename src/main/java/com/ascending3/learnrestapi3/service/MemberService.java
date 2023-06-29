package com.ascending3.learnrestapi3.service;

import com.ascending3.learnrestapi3.dto.CourseDto;
import com.ascending3.learnrestapi3.dto.MemberDto;
import com.ascending3.learnrestapi3.entity.Course;
import com.ascending3.learnrestapi3.entity.Member;

import java.util.List;

public interface MemberService {
    MemberDto save(MemberDto memberDto, Long coachId);
    MemberDto update(MemberDto memberDto);
    boolean deleteByLoginName(String loginName);
    boolean deleteById(Long memberId);
    boolean delete(MemberDto memberDto);
    List<MemberDto> getMembers();
    MemberDto getMemberById(Long id);
    MemberDto getMemberByLoginName(String loginName);

    List<MemberDto> getMembersByCoachId (Long coachId);
    List<CourseDto> getAssociatedCoursesByMemberId(Long memberId);
    MemberDto getMemberWithAssociatedCoursesByMemberId(Long memberId);

}
