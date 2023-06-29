package com.ascending3.learnrestapi3.dao.impl.hibernate;

import com.ascending3.learnrestapi3.dao.CoachDao;
import com.ascending3.learnrestapi3.entity.Coach;
import com.ascending3.learnrestapi3.util.HQLStatementUtil;
import com.ascending3.learnrestapi3.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("CoachDaoHibernateImpl")

public class CoachDaoHibernateImpl implements CoachDao {

    private Logger logger = LoggerFactory.getLogger(CoachDaoHibernateImpl.class);

    @Override
    public Coach save(Coach coach) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.save(coach);
            transaction.commit();
        } catch (Exception e){
            logger.error("fail to insert a coach, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return coach;
    }

    @Override
    public Coach update(Coach coach) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(coach);
            transaction.commit();
        } catch (Exception e){
            logger.error("fail to update a coach, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return coach;
    }

    @Override
    public boolean deleteByName(String coachName) {
        return false;
    }

    @Override
    public boolean deleteById(Long coachId) {
        return false;
    }

    @Override
    public boolean delete(Coach coach) {
        boolean deleteResult = false;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.delete(coach);
            transaction.commit();
        } catch (Exception e){
            logger.error("fail to delete a coach, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return deleteResult;
    }

    @Override
    public List<Coach> getCoaches() {
        List<Coach> coachList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            Query<Coach> query = session.createQuery(HQLStatementUtil.HQL_SELECT_ALL_COACHES);
            coachList = query.list();
        } catch (HibernateException he){
            logger.error("fail to retrieve all coach, error={}", he.getMessage());
        }finally {
            session.close();
        }
        if(coachList == null)
            coachList = new ArrayList<Coach>();
        return coachList;
    }

    @Override
    public Coach getCoachById(Long coachId) {
        Coach coach = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            Query<Coach> query = session.createQuery(HQLStatementUtil.HQL_SELECT_COACH_BY_ID);
            query.setParameter("id", coachId);
            coach = query.uniqueResult();
        } catch (HibernateException he){
            logger.error("fail to retrieve coach with id, error={}", coachId, he.getMessage());
        }finally {
            session.close();
        }
        return coach;
    }

    @Override
    public Coach getCoachByName(String coachName) {
        return null;
    }

    @Override
    public Coach getCoachAndMembersAndCoursesByCoachId(Long coachId) {
        Coach coach = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try{
            Query<Coach> query = session.createQuery(HQLStatementUtil.HQL_SELECT_COACH_WITH_CHILDREN_BY_COACH_ID);
            query.setParameter("id", coachId);
            coach = query.uniqueResult();
        } catch (HibernateException he){
            logger.error("fail to retrieve coach member course with id, error={}", coachId, he.getMessage());
        }finally {
            session.close();
        }
        return coach;
    }

    @Override
    public Coach getCoachAndMembersAndCoursesByCoachName(String coachName) {
        return null;
    }
}
