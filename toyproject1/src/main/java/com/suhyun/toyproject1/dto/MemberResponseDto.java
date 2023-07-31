package com.suhyun.toyproject1.dto;

import com.suhyun.toyproject1.domain.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MemberResponseDto {

    private String id; //PK  
    private String password;
    private String role;
    private String username;
    
    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.password = member.getPassword();
        this.role = member.getRole();
        this.username = member.getUsername();
    }
}
