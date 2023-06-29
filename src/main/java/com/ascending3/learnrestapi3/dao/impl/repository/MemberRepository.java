package com.ascending3.learnrestapi3.dao.impl.repository;

import com.ascending3.learnrestapi3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
