//package com.skyfox83.springbootdeveloper.config;
//
//import com.skyfox83.springbootdeveloper.service.UserDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@RequiredArgsConstructor
//@Configuration
////@EnableWebSecurity      // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
///* 실제 인증 처리하는 시큐리티 설정 파일 */
//public class WebSecurityConfig {
//
//    private final UserDetailService userDetailService;
//
//    // 스프링 시큐리티 기능 비활성화 -> 스프링 시큐리티 모든 기능 비활성화.
//    // 인증,인가 서비스를 '모든' 곳에 적용하지 않음
//    // 일반적으로 정적 리소스(이미지, html 파일)에 설정. static 하위 경로 내 리소스 혹은 h2콘솔 하위 url 대상으로 사용
//    @Bean
//    public WebSecurityCustomizer configure() {
//        /*
//        // 책 예제 - 작동 안됨
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**");
//        */
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers(
//                        new AntPathRequestMatcher("/static/**"),
//                        new AntPathRequestMatcher("/img/**"),
//                        new AntPathRequestMatcher("/css/**"),
//                        new AntPathRequestMatcher("/js/**")
//                );
//    }
//
//    // 특정 http 요청에 대한 웹 기반 보안 구성
//    // 요 메서드에서 인증/인가, 로그인/로그아웃 관련 설정 가능
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        /*
//        // 책 예제 - 작동 안됨
//        return http
//                .authorizeRequests()    // 인증, 인가 설정
//                    .requestMatchers("/login", "/signup", "/user").permitAll()  // requestMatchers() 에 파라미터로 전달된 url에 대해 설정
//                    .anyRequest().authenticated()
//                .and()
//                .formLogin()    // 폼 기반 로그인 설정
//                    .loginPage("/login")    // 로그인 페이지 경로 설정
//                    .defaultSuccessUrl("/articles") // 로그인 완료 후 이동할 경로 설정
//                .and()
//                .logout()   // 로그아웃 설정
//                    .logoutSuccessUrl("/login") // 로그아웃 완료 후 이동할 경로 설정
//                    .invalidateHttpSession(true)    // 로그아웃 이후 세션 삭제 여부
//                .and()
//                .csrf().disable()   // csrf 비활성화 (실습용. csrf 공격 방지를 위해서는 활성화하는게 좋음) - token을 사용하는 방식에서는 csrf를 비활성화
//                .build();
//        */
//
//
//        // 수정1 - 안됨
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
//                        .requestMatchers(
//                                new AntPathRequestMatcher("/login"),
//                                new AntPathRequestMatcher("/signup"),
//                                new AntPathRequestMatcher("/user")
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/articles")
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true)
//                )
//                .build();
//    }
//
//    // 인증 관리자 관련 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
//
//    // 패스워드 인코더로 사용할 빈 등록
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

// OAuth2와 JWT를 함께 사용하기 위해 기존 '폼 로그인 방식' 구현 소스 모두 주석 처리 (23.11.27)
