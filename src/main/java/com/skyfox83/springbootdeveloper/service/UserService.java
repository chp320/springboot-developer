package com.skyfox83.springbootdeveloper.service;

import com.skyfox83.springbootdeveloper.domain.User;
import com.skyfox83.springbootdeveloper.dto.AddUserRequest;
import com.skyfox83.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    // 주입
    private final UserRepository userRepository;
    // 아래 주석 처리, 23.12.04 - save() 메서드에 BCryptPasswordEncoder를 생성자를 사용해서 직접 생성하는 것으로 변경
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원정보 추가
    public Long save(AddUserRequest dto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
//                .password(bCryptPasswordEncoder.encode(dto.getPassword()))  // 패스워드 암호화
                        .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    // 메서드 추가, 23.12.04
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    // 유저id 조회
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
