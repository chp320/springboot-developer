package com.skyfox83.springbootdeveloper.dto;

import com.skyfox83.springbootdeveloper.domain.Article;
import lombok.Getter;

/* 응답을 위한 DTO - Article 테이블에서 데이터 조회 후 entity를 인수로 받아 response로 전달 */
@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
