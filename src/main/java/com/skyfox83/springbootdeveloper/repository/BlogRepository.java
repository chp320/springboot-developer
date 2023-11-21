package com.skyfox83.springbootdeveloper.repository;

import com.skyfox83.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
