package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    /*optional은 java8에 들어간 기능
        -만약 find로 찾은값이 null일 수도 있는데
        null을 처리하는 방법 중에서 null을 그래도 내리는거보단
        Optional로 감싸서 내리는 방법을 선호한다.

     */
}
