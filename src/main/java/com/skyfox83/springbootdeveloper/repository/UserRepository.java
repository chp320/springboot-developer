package com.skyfox83.springbootdeveloper.repository;

import com.skyfox83.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 스프링 데이터 JPA 는 메서드 규칙에 맞춰 선언된 메서드명을 분석해서 자동으로 쿼리 생성함
    Optional<User> findByEmail(String email);   // from users where email = #{email}
}
