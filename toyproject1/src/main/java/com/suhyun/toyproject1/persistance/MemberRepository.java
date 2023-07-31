package com.suhyun.toyproject1.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;

import com.suhyun.toyproject1.domain.Member;
import com.suhyun.toyproject1.dto.MemberResponseDto;

public interface MemberRepository extends JpaRepository<Member, String> {

    MemberResponseDto findByIdAndPassword(final String id, final String password);
    
}
