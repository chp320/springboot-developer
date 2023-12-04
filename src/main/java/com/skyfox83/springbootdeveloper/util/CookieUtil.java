package com.skyfox83.springbootdeveloper.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

/**
 * 쿠키 관리 클래스
 * - OAuth2 인증 플로우 진행 시 사용할 '쿠키' 관리 클래스
 */
public class CookieUtil {

    /**
     * 쿠키 추가 메서드
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);     // HTTP response 에 쿠키 추가
    }

    /**
     * 쿠키 삭제 메서드 (실제 쿠키를 삭제하는 방법은 없고, '키'로 전달된 쿠키의 '값'을 빈값 혹은 0 으로 설정)
     * @param request
     * @param response
     * @param name
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 객체를 쿠키의 값으로 변환 (직렬화)
     * @param obj
     * @return
     */
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(obj));
    }

    /**
     * 쿠키를 객체로 변환 (역직렬화)
     * @param cookie
     * @param cls
     * @return
     * @param <T>
     */
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
