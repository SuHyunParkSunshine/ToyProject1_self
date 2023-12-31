package com.suhyun.toyproject1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.suhyun.toyproject1.domain.Member;
import com.suhyun.toyproject1.persistance.MemberRepository;

@SpringBootTest
public class MemberInitialiser {
    
    @Autowired
    private MemberRepository memRepo;

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void testDataInsert() {
        memRepo.save(Member.builder()
                        .id("member12")
                        .username("member")
                        .password(encoder.encode("1234"))
                        .role("ROLE_MEMBER")
                        .enabled(true).build());
        memRepo.save(Member.builder()
                        .id("manager12")
                        .username("manager")
                        .password(encoder.encode("1234"))
                        .role("ROLE_MANAGER")
                        .enabled(true).build());
        memRepo.save(Member.builder()
                        .id("admin12")
                        .username("admin")
                        .password(encoder.encode("1234"))
                        .role("ROLE_ADMIN")
                        .enabled(true).build());
    

    }
}
