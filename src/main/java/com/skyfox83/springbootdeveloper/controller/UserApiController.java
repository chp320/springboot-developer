package com.skyfox83.springbootdeveloper.controller;

import com.skyfox83.springbootdeveloper.dto.AddUserRequest;
import com.skyfox83.springbootdeveloper.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * api 컨트롤러
 * - 회원 가입 form에서 회원 가입 요청을 받아
 * - 서비스 메서드를 사용해서 사용자 정보 저장하고
 * - 로그인 페이지로 이동시키는 역할
 */

@RequiredArgsConstructor
@Controller
public class UserApiController {

    // 주입
    private final UserService userService;

    @PostMapping("/user")
    public String singup(AddUserRequest request) {
        userService.save(request);  // 회원 가입 메서드 호출
        return "redirect:/login";   // 회원 가입 완료 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
