package com.skyfox83.springbootdeveloper.service;

import com.skyfox83.springbootdeveloper.domain.RefreshToken;
import com.skyfox83.springbootdeveloper.repository.RefreshTokenRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 전달받은 refresh token으로 refresh token 객체를 검색하는 클래스
 */
@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
