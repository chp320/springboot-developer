package com.skyfox83.springbootdeveloper.config.oauth;

import com.skyfox83.springbootdeveloper.domain.User;
import com.skyfox83.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 리소스 서버에서 보내온 '사용자 정보'를 토대로 '우리 서비스' 내
 * users 테이블에 사용자 정보가 있다면 이름 업데이트, 없다면 데이터 추가
 */
@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 요청을 바탕으로 사용자 정보 조회. 사용자 정보 담은 객체 반환(OAuth2User)
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);
        return user;
    }

    /**
     * 전달받은 사용자 정보를 토대로 users 테이블 내 데이터 존재하면 update, 없으면 save 수행
     * @param oAuth2User
     * @return
     */
    private User saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name))
                .orElse(User.builder()
                        .email(email)
                        .nickname(name)
                        .build());
        return userRepository.save(user);
    }
}
