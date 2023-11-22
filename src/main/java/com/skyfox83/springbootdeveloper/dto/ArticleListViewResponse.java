package com.skyfox83.springbootdeveloper.dto;

import com.skyfox83.springbootdeveloper.domain.Article;
import lombok.Getter;

/**
 * '뷰'에게 데이터 전달하기 위한 객체
 */
@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
