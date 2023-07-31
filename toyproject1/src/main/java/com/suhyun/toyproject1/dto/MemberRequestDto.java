package com.suhyun.toyproject1.dto;

import com.suhyun.toyproject1.domain.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MemberRequestDto {

    private String id;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .password(password)
                .build();
    }    
}
