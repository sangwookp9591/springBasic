package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    //jpa는 EntityManager로 모든게 동작을 한다.
    //jpa라이브러리를 받으면 spring boot가 자동으로 entitymanager를 생성해준다.
    //properties에 등록한 정보와 dbConnection 정보랑 짬뽕해서 Spring boot가ㅣ entitymanager를 생성해준다.
    //우리는 만들어준것을 injection받으면 된다/
    @Override
    public Member save(Member member) {
        em.persist(member); //persist 뜻 연속하다 영구저장하다.
        return member;
        /*
        * 이렇게 하면 끝난다? 이렇게 하면 JPA가 INSERT쿼리 만들어 서 집어넣고
        * ID까지 SETID까지해줌
        *
        * */
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회할 타입이랑 식별자 pk값 넣어주면 조회가 된다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {

        List<Member> result  = em.createQuery("select m from Member m where m.name= :name",Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
        /*
        * Member.class -> 조회할 대상
        * 
        * */
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        //jpql이라는 쿼리 언어이다 테이블 대상이아니라 객체를 대상으로 쿼리를 날리면 이게 sql로 번역된다.
        // Member as m -> member enitity를 대상으로 조회해
        // select m from Member m -> Member ENtity 자체를 조회하는 것이다.
    }
    //저장 조회 업데이트는 쿼리를 따로 짤 필요가 없지만,
    //여러개의 리스트를 찾을때는 pk기반이아닌 나머지는 jpql를 작성해야한다.
    //spring data jpa를사용하면 다건조회도  jpql를 사용하지 않아도 돈다.
    //
}
