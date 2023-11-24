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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {    // 쿼리 파라미터에 값이 있을수도 없을수도 있는데 있으면 id에 맵핑해라
        if (id == null) {
            // id가 없다는 것은 새 글 등록하는 액션임
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            // id가 있으면 글을 수정하는 액션임
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
