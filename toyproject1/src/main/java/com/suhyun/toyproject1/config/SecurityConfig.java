package com.suhyun.toyproject1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.suhyun.toyproject1.config.auth.JWTAuthorizationFilter;
import com.suhyun.toyproject1.config.filter.JWTAuthenticationFilter;
import com.suhyun.toyproject1.persistance.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private MemberRepository memRepo;

    // AuthenticationManager를 얻기 위해 DI 받는 객체
    @Autowired
    private AuthenticationConfiguration authConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화(JS에서 호출 가능)
        http.cors(cors -> cors.disable()); // CORS 보호 비활성화(React에서 호출 가능) : RestAPI로 호출할 때

        http.authorizeHttpRequests(security -> {
            security.anyRequest().permitAll();
        });

        // 웹페이지 접근 권한 설정
        // http.authorizeHttpRequests(security -> {
        //     security.requestMatchers("/member/**").authenticated()
        //             .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
        //             .requestMatchers("/admin/**").hasRole("ADMIN")
        //             .anyRequest().permitAll();
        // });
            //  /member와 하위 주소는 로그인한 사용자는 모두 접근 가능
            //  /manager와 하위 주소는 로그인한 ROLE_MANAGER, ROLE_ADMIN 권한을 가진 사용자만 접근 가능
            //  /admin과 하위 주소는 로그인한 ROLE_ADMIN 권한을 가진 사용자만 접근 가능
            //  위에서 설정한 이외 모든 주소는 접근 허용

        http.formLogin(frmLogin-> {
            frmLogin.disable(); // Form을 이용한 로그인을 사용하지 않겠다는 설정
        });

        // Security Session 만들지 않겠다고 설정
        http.sessionManagement(ssmg -> ssmg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 시큐리티 인증 필터 등록
        // JWTAuthenticationFilter의 인증 메소드 attemptAuthentication에서 인증을 실행하기 위해 AuthenticationManager를 넘긴다.
        http.addFilter(new JWTAuthenticationFilter(authConfig.getAuthenticationManager()));

        // 시큐리티 인가 필터 등록
        http.addFilter(new JWTAuthorizationFilter(authConfig.getAuthenticationManager(), memRepo));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
