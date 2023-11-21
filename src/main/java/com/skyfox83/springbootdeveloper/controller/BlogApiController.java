package com.skyfox83.springbootdeveloper.controller;

import com.skyfox83.springbootdeveloper.domain.Article;
import com.skyfox83.springbootdeveloper.dto.AddArticleRequest;
import com.skyfox83.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController             // HTTP Response Body 에 객체 데이터를 json 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST 인 경우, 전달받은 URL과 동일하면 메서드로 맵핑
    @PostMapping("/api/articles")
    // 요청 본문 값 맵핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었고, 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
}
