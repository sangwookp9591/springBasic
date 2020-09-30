package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
    /*
    * JpaRepository<Key,식별자 pk>
    * */

    @Override
    Optional<Member> findByName(String name);
    //JpaRepository 가 공통 인터페이스를 통하CRUD를 제공한다(페이지기능도 제공).
    // 공통으로 제공할수없는것은 따로 만들어야한다.
    //select m from Member m where m.name = ?
    //로 짜서 제공을 해준다.
    //interface 이름만으로 개발이 끝난다.


    //순수 Jdbc 쿼리하나가 어마어마함
    //JdbcTemplate은 반복된는 쿼리가 어마어마하게 줄어들지만 sql을 내가 직접작성해야한다.
    //JPA는 기본적인 CRUD를하는데 내가 쿼리를 작성할 필요가 없었음.
    //Spring data jpa로 오니깐 아에내가 구현클래스를 작성할 필요도없이 interface로 끝남.


   /*
    * 이게 끝?
    * 인터페이스말고 구현체도 없고  다른메소드는 어디에?..?
    *
    * spring data jpa가 JpaRepository를 받고 있으면 구현체를 자동으로 만들어 준다.
    * spring bean을 자동으로 등록한다.
    * 우리는 이걸 가져다쓰면된다.
    *
    * 이렇게 SpringDataJpaMemberRepository인터페이스만 만들어놓으면
    * JpaRepository(spring data 가제공하는)를 해놓으면
    * spring data. jpa가 인터페이스에대한 구현체를 자체기술로 만들고 spring bean에 등록해놓는다.
    * 우리는 이걸 injection 받아서 service에 등록하면된다.
    * */
}
