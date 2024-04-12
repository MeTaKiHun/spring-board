package com.board.shop.Config;


import com.board.shop.Encoder.UserPasswordEncoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.naming.AuthenticationException;
import java.io.IOException;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/join/**").permitAll()
                        .requestMatchers("/main/**").hasAnyRole("3", "2","1")
                        .requestMatchers("/board/**").hasAnyRole("3", "2","1")
                        .requestMatchers("/vip/**").hasAnyRole("3", "2")
                        .requestMatchers("/admin/**").hasRole("3")
                        .anyRequest().authenticated()
                );
        http
                .formLogin(form -> form
                        .loginPage("/join/login")
                        .loginProcessingUrl("/join/login") // 로그인 처리 URL 설정
                        .usernameParameter("userid")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/main/main", true)
                        .failureUrl("/join/login?error=true")
                        .failureHandler(new CustomAuthenticationFailureHandler())
                        .permitAll()
                );


        http
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        // 로그아웃 핸들러 추가 (세션 무효화 처리)
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            session.invalidate();
                        })
                        // 로그아웃 성공 핸들러 추가 (리다이렉션 처리)
                        .logoutSuccessHandler((request, response, authentication) ->
                                response.sendRedirect("/join/login"))
                        // 로그아웃 시 쿠키 삭제 설정 (예: "remember-me" 쿠키 삭제)
                        .deleteCookies("remember-me")
                );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new UserPasswordEncoder();
    }


    public void loginfail(HttpServletRequest request, AuthenticationException exception) throws IOException,Exception{
        String id = request.getParameter("userid");
        String pwd = request.getParameter("password");
        System.out.println("pwd = " + pwd);
        System.out.println("id = " + id);
    }
}
