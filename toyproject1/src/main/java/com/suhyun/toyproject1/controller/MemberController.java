package com.suhyun.toyproject1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suhyun.toyproject1.dto.MemberRequestDto;
import com.suhyun.toyproject1.dto.MemberResponseDto;
import com.suhyun.toyproject1.service.MemberService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memService;

    @PostMapping("/login")
    public MemberResponseDto login(@RequestBody final MemberRequestDto params) {
        MemberResponseDto entity = memService.findBy(params);
        return entity;
    }    
}
