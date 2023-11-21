package com.skyfox83.springbootdeveloper.service;

import com.skyfox83.springbootdeveloper.domain.Article;
import com.skyfox83.springbootdeveloper.dto.AddArticleRequest;
import com.skyfox83.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor    // final 이 붙거나 @NotNull 이 붙은 필드의 생성자 추가.... 한다는데 뭔말이여?? -_-;;
@Service                    // 요 클래스를 빈으로 등록하겠다!!
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());     // JpaRepository 에서 지원하는 저장 메서드 save() 로 AddArticleRequest 클래스에 저장된 값을 article DB에 저장
    }
}
