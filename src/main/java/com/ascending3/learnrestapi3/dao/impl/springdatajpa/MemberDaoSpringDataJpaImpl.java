package com.ascending3.learnrestapi3.dao.impl.springdatajpa;

import com.ascending3.learnrestapi3.dao.MemberDao;
import com.ascending3.learnrestapi3.dao.impl.repository.CourseRepository;
import com.ascending3.learnrestapi3.dao.impl.repository.MemberRepository;
import com.ascending3.learnrestapi3.entity.Course;
import com.ascending3.learnrestapi3.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MemberDaoSpringDataJpaImpl")
public class MemberDaoSpringDataJpaImpl implements MemberDao {

    private Logger logger = LoggerFactory.getLogger(MemberDaoSpringDataJpaImpl.class);


    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member save(Member member, Long coachId) {
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    @Override
    public Member update(Member member) {
        Member updatedMember = memberRepository.save(member);
        return updatedMember;
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
    public boolean delete(Member member) {
        return false;
    }

    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public Member getMemberById(Long id) {
        return null;
    }

    @Override
    public Member getMemberByLoginName(String loginName) {
        return null;
    }

    @Override
    public List<Member> getMembersByCoachId(Long coachId) {
        return null;
    }

    @Override
    public List<Course> getAssociatedCoursesByMemberId(Long memberId) {
        return null;
    }

    @Override
    public Member getMemberWithAssociatedCoursesByMemberId(Long memberId) {
        return null;
    }
}
