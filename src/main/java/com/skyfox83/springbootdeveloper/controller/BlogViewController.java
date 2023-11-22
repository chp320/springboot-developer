package com.skyfox83.springbootdeveloper.controller;

import com.skyfox83.springbootdeveloper.domain.Article;
import com.skyfox83.springbootdeveloper.dto.ArticleListViewResponse;
import com.skyfox83.springbootdeveloper.dto.ArticleViewResponse;
import com.skyfox83.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);   // 등록된 모든 블로그 글 리스트 저장

        return "articleList";   // articleList.html 뷰 조회
    }

    /* 블로그 글 상세보기 할 뷰 반환 */
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
}
