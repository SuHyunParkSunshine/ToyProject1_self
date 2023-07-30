package com.suhyun.toyproject1.config.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.suhyun.toyproject1.persistance.MemberRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

    // 사용자 정보를 읽기 위한 인터페이스 추가
    private MemberRepository memRepo;
    
    // 생성자는 AuthenticationManager와 MemberRepository를 파라미터로 요구
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memRepo) {
        super(authenticationManager); //super() 자식 클래스에서 부모 클래스의 생성자를 호출하는 키워드
        this.memRepo = memRepo;
    }

    // 생성자는 부모 클래스가 AuthentiationManager 객체를 요구한다.
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    // 필터링 매소드를 오버라이드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

        String srcToken = request.getHeader("Authorization");
        if(srcToken == null || !srcToken.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

    }

}
