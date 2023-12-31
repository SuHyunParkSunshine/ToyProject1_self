package com.suhyun.toyproject1.config.auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.suhyun.toyproject1.domain.Member;
import com.suhyun.toyproject1.persistance.MemberRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String srcToken = request.getHeader("Authorization");
        if(srcToken == null || !srcToken.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        String jwtToken = srcToken.replace("Bearer ", "");
        String username = JWT.require(Algorithm.HMAC256("com.suhyun.toyproject1.jwtkey")).build().verify(jwtToken).getClaim("username").asString();

        // 토큰에서 얻은 username으로 DB를 검색해서 사용자를 찾고
        Optional<Member> opt = memRepo.findById(username);
        if(!opt.isPresent()) {
            chain.doFilter(request, response);
            return;
        }

        Member findMember = opt.get();
        //DB에서 읽은 사용자 정보를 이용해서 UserDetails 타입의 객체를 만들어서
        User user = new User(findMember.getUsername(), findMember.getPassword(), findMember.getAuthorities());
        //Authentication 객체를 생성 : 사용자명과 권한 관리를 위한 정보를 입력(암호는 필요 없음)
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        // 시큐리티 세션에 등록한다.
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);

    }

}
