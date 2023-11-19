package com.skyfox83.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 엔티티는 "반드시" 기본 생성자가 있어야 하고, 접근 제어자는 public 또는 protected로 해야하는데.. 더 안전한 protected를 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
// Member 객체를 JPA가 관리하는 엔티티로 지정. Member클래스-실제DB테이블 맵핑. 클래스명과 동일한 테이블명과 맵핑되며, name 속성으로 값을 지정하면 해당 테이블로 맵핑
// ex) @Entity (name="member_list") -> member_list 테이블과 맵핑
@Entity
public class Member {
    @Id
    // 기본키 생성 방식 (auto(default), identity, sequence, table)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;        // DB 테이블의 id 컬럼과 매칭

    @Column(name = "name", nullable = false)
    private String name;    // DB 테이블의 name 컬럼과 매칭
}
