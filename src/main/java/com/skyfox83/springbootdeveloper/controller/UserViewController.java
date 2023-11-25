package com.skyfox83.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * view 컨트롤러
 * - 로그인/회원가입 경로로 접근하면 노출할 view file을 연결하는 컨트롤러
 */

@Controller
public class UserViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
