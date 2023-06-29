package com.ascending3.learnrestapi3.dao.impl.hibernate;

import com.ascending3.learnrestapi3.dao.CourseDao;
import com.ascending3.learnrestapi3.entity.Course;
import com.ascending3.learnrestapi3.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CourseDaoHibernateImpl")

public class CourseDaoHibernateImpl implements CourseDao {
    private Logger logger = LoggerFactory.getLogger(CourseDaoHibernateImpl.class);

    @Override
    public Course save(Course course) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        } catch (Exception e){
            logger.error("fail to insert a course, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return course;
    }

    @Override
    public Course update(Course course) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(course);
            transaction.commit();
        } catch (Exception e){
            logger.error("fail to insert a coach, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return course;
    }

    @Override
    public boolean deleteByName(String courseName) {
        return false;
    }

    @Override
    public boolean deleteById(Long courseId) {
        return false;
    }

    @Override
    public boolean delete(Course course) {
        return false;
    }

    @Override
    public List<Course> getCourses() {
        return null;
    }

    @Override
    public Course getCourseById(Long id) {
        return null;
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
