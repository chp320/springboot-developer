package com.skyfox83.springbootdeveloper.controller;

import com.skyfox83.springbootdeveloper.domain.Article;
import com.skyfox83.springbootdeveloper.dto.AddArticleRequest;
import com.skyfox83.springbootdeveloper.dto.ArticleResponse;
import com.skyfox83.springbootdeveloper.dto.UpdateArticleRequest;
import com.skyfox83.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController             // HTTP Response Body 에 객체 데이터를 json 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST 인 경우, 전달받은 URL과 동일하면 메서드로 맵핑
    @PostMapping("/api/articles")
    // 요청 본문 값 맵핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal) {
        Article savedArticle = blogService.save(request, principal.getName());

        // 요청한 자원이 성공적으로 생성되었고, 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    // 전체 글을 조회하는 api 호출한 경우 메서드 맵핑
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()   // 자바8의 기능. 컬렉션을 간편하게 처리하기 위한 기능
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    // id로 GET 요청 인입 시, 해당 글 조회를 위해 메서드 맵핑
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        // @PathVariable - 애너테이션 뒤에 따라오는 변수명을 URL에서 동일한 변수명을 찾아서 인수로 처리함
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));    // 요청한 데이터를 찾으면 Response body 에 담아 브라우저로 전송
    }

    // id로 DELETE 요청 인입 시, 해당 글 삭제 위해 메서드 맵핑
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    // id로 PUT 요청 인입 시, 해당 글 수정 위해 메서드 맵핑
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }
}
