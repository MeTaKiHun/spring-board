package com.board.shop.Config;


import com.board.shop.Encoder.UserPasswordEncoder;
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
                        .loginProcessingUrl("/join/login_proc") // 로그인 처리 URL 설정
                        .usernameParameter("userid")
                        .passwordParameter("pwd")
                        .defaultSuccessUrl("/main/main", true)
                        .failureUrl("/join/login?error=true") // 로그인 실패 시 리다이렉트할 URL 설정
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
    /*    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체에는 사용자 이름, 권한 등의 정보가 있습니다.
            String username = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // ... 이후 username과 authorities를 원하는 방식으로 사용합니다 ...
        }*/

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new UserPasswordEncoder();
    }
}
