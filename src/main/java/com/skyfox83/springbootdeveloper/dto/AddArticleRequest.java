package com.skyfox83.springbootdeveloper.dto;

import com.skyfox83.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor      // lombok 제공 '기본 생성자' 추가
@AllArgsConstructor     // lombok 제공 '모든 필드 값을 파라미터로 받는 생성자' 추가
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    // 빌더 패턴을 이용해서 엔티티로 생성
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
