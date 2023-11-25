package com.skyfox83.springbootdeveloper.service;

import com.skyfox83.springbootdeveloper.domain.User;
import com.skyfox83.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 사용자 정보 가져오는 기능
public class UserDetailService implements UserDetailsService {

   private final UserRepository userRepository;

    // 사용자 이름(email)으로 사용자 정보 가져오는 메서드
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new IllegalArgumentException(email));
    }
}
