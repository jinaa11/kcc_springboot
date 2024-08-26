package com.kcc.security2.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcc.security2.config.PrincipalDetail;
import com.kcc.security2.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // 로그인 시도 시 호출(/login)
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attemptAuthentication 호출");
        // 1. username, password 받기 => 인증 처리
        try {
            // Json 데이터 파싱
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);

            // 토큰으로 만들기
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            // 인증을 할 준비 작업
            // 2. PrincipalDetailService loadUserByUsername() 호출
            // authentication: PrincipalDetail을 받음
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // 3. principalDetail 구하기
            PrincipalDetail principalDetail = (PrincipalDetail) authentication.getPrincipal();
            System.out.println("principalDetail: " + principalDetail);

            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 로그인되면 자동 호출
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("로그인 성공");
        // JWT Token 생성, 전송
        // 인증 정보 얻어오기
        PrincipalDetail principalDetail = (PrincipalDetail) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("kosaToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 10))) // 10분 유효기간
                .withClaim("id", principalDetail.getUser().getId())
                .withClaim("username", principalDetail.getUser().getUsername())
                .sign(Algorithm.HMAC512("kcc"));

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}
