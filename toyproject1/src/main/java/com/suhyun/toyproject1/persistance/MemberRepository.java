package com.suhyun.toyproject1.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;

import com.suhyun.toyproject1.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    
}
