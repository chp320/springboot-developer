package com.skyfox83.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

/**
 * 타임리프 문법 익히기 용도 컨트롤러
 * - /thymeleaf/example GET 요청 시, 특정 데이터를 "뷰(HTML)로 넘기는 모델 객체"에 추가하는 컨트롤러
 */
@Controller         // 컨트롤러임을 명시
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {   // 뷰로 데이터를 넘기는 '모델' 객체
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson);        // Person 객체 저장
        model.addAttribute("today", LocalDate.now());

        return "example";       // example.html 이라는 뷰 조회 즉, 뷰의 이름을 의미. -> resource/templates 디렉토리에서 해당 파일을 찾은 후 브라우저에 전달
    }

    @Getter
    @Setter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
