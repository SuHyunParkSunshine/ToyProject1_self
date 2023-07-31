package com.suhyun.toyproject1.service;

import org.springframework.stereotype.Service;

import com.suhyun.toyproject1.dto.MemberRequestDto;
import com.suhyun.toyproject1.dto.MemberResponseDto;
import com.suhyun.toyproject1.persistance.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    //final 붙여야지 생성자 만들어줌
    private final MemberRepository memRepo;

    public MemberResponseDto findBy(final MemberRequestDto params) {
        MemberResponseDto entity = memRepo.findByIdAndPassword(params.getId(), params.getPassword());
        return entity;
    }    
}
