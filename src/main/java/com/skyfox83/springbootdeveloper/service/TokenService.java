package com.skyfox83.springbootdeveloper.service;

import com.skyfox83.springbootdeveloper.config.jwt.TokenProvider;
import com.skyfox83.springbootdeveloper.domain.User;
import com.skyfox83.springbootdeveloper.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    /**
     * 새로운 액세스 토큰을 생성하는 클래스
     * - 전달받은 refreshToken으로 유효성 검사 진행
     * - 유효한 토큰인 경우, refreshToken으로 사용자id 찾음
     * - 토큰 제공자의 generateToken() 메서드 호출하여 새로운 액세스 토큰 발급
     * @param refreshToken
     * @return 새로운 액세스 토큰
     */
    public String createNewAccessToken(String refreshToken) {

        // 토큰 유효성 검사 실패 시, 예외 리턴
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        // 리프레시 토큰이 유효한 경우, 해당 토큰으로 사용자id 찾음
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();

        // 사용자id 로 유효한 사용자인지 확인
        User user = userService.findById(userId);

        // 유효한 사용자인 경우, 새로운 액세스 토큰 발급 (유효시간: 2시간)
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
