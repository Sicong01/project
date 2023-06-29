package com.ascending3.learnrestapi3.dao.impl.hibernate;

import com.ascending3.learnrestapi3.dao.MemberDao;
import com.ascending3.learnrestapi3.entity.Course;
import com.ascending3.learnrestapi3.entity.Member;
import com.ascending3.learnrestapi3.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MemberDaoHibernateImpl")
public class MemberDaoHibernateImpl implements MemberDao {

    private Logger logger = LoggerFactory.getLogger(MemberDaoHibernateImpl.class);


    @Override
    public Member save(Member member, Long coachId) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.persist(member);
            transaction.commit();
        } catch (Exception e){
            logger.error("fail to insert a member, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return member;
    }

    @Override
    public Member update(Member member) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(member);
            transaction.commit();
        } catch (Exception e){
            logger.error("fail to update a member, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return member;
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
