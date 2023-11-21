package com.skyfox83.springbootdeveloper.domain;

/* Article 테이블과 맵핑하기 위한 엔티티 클래스 */
/*
    Article 테이블 구조 - 컬럼명|자료형|null허용|키|설명
    id | BIGINT | n | 기본키 | 일련번호. 기본키
    title | VARCHAR(255) | n | | 게시물의 제목
    content | VARCHAR(255) | n | | 내용

 */

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // 빌더 패턴으로 객체 생성
    @Builder    // lombok 에서 제공하는 애너테이션
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 요청받은 내용으로 값을 수정하는 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
/*  @Getter, @NoArgsConstructor 애너테이션 대치를 위해 주석 처리
    // 기본 생성자 -> @NoArgsConstructor(access = AccessLevel.PROTECTED) 로도 작성 가능
    protected Article() {}

    // getter

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
 */
}
