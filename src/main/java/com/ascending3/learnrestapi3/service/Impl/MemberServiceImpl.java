package com.ascending3.learnrestapi3.service.Impl;

import com.ascending3.learnrestapi3.dto.CourseDto;
import com.ascending3.learnrestapi3.dto.MemberDto;
import com.ascending3.learnrestapi3.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public MemberDto save(MemberDto memberDto, Long coachId) {
        return null;
    }

    @Override
    public MemberDto update(MemberDto memberDto) {
        return null;
    }

    @Override
    public boolean deleteByLoginName(String loginName) {
        return false;
    }

    @Override
    public boolean deleteById(Long memberId) {
        return false;
    }

    @Override
    public boolean delete(MemberDto memberDto) {
        return false;
    }

    @Override
    public List<MemberDto> getMembers() {
        return null;
    }

    @Override
    public MemberDto getMemberById(Long id) {
        return null;
    }

    @Override
    public MemberDto getMemberByLoginName(String loginName) {
        return null;
    }

    @Override
    public List<MemberDto> getMembersByCoachId(Long coachId) {
        return null;
    }

    @Override
    public List<CourseDto> getAssociatedCoursesByMemberId(Long memberId) {
        return null;
    }

    @Override
    public MemberDto getMemberWithAssociatedCoursesByMemberId(Long memberId) {
        return null;
    }
}
