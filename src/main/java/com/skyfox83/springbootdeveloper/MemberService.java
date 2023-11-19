package com.skyfox83.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * '스프링 데이터'를 통해서 DB 사용 기능을 추상화하였음
 * 스프링 데이터 JPA: Repository 역할을 하는 '인터페이스'를 만들어 DB 테이블 조회/수정/생성/삭제 등의 작업 수행
 *
 * 앞에서 작성한 MemberRepository 클래스는 스프링데이터 인터페이스인 JpaRepository를 확장한 인터페이스임.
 */
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public void test() {
        // 생성 (Create) - 데이터 객체 저장
        memberRepository.save(new Member(1L, "A"));

        // 조회 (Read)
        Optional<Member> member = memberRepository.findById(1L);    // 단건 조회
        List<Member> allMembers = memberRepository.findAll();       // 전체 조회

        // 삭제 (Delete)
        memberRepository.deleteById(1L);
    }
}
