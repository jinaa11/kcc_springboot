package com.kcc.security1.config;

import com.kcc.security1.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.file.Path;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    private static final String[] WHITE_LIST = {
            "/",
            "/common/**",
            "/WEB-INF/views/**",
            "/joinForm",
            "/join",
            "/loginForm",
            "/h2-console/**"
    };

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(WHITE_LIST).permitAll()
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .anyRequest().authenticated()
                // h2 DB가 Frameset에서 인식하지 못 하는 것(깨지는 것)을 보이게 해라
                ).csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .formLogin(form -> form.loginPage("/loginForm")
                        // 시큐리티가 인증을 함
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/main"))
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/loginForm")
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint.userService(principalOauth2UserService))
                );

        return http.build();

        // url 단위
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(authorize ->
//                                authorize.requestMatchers(WHITE_LIST).permitAll()
//                                        .requestMatchers("/manager/**").hasAnyRole("ROLE_MANAGER", "ROLE_ADMIN")
//                                        .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
//                                        .requestMatchers(PathRequest.toH2Console()).permitAll()
//                                        .anyRequest().authenticated()
//                ).csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
//                .formLogin(form -> form.loginPage("/loginForm")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/main"));
//
//        return http.build();

//               http.authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/common/**").permitAll()
//                                .requestMatchers("/user/**").authenticated()
//                                .requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                                .requestMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                                .anyRequest().permitAll()
//                )
//                .formLogin(form -> form.loginPage("/loginForm"));
//        return http.build();
    }

}
