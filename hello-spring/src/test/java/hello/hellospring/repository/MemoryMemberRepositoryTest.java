package hello.hellospring.repository;

import hello.hellospring.domain.Member;
/*import org.junit.jupiter.api.Assertions;*/
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.TypeComparators;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository =  new MemoryMemberRepository();
    private TypeComparators store;

    @AfterEach //메서드가 실행이 끝날때마다 어떤동작이 실행하게 하는것 callback,이랑 비슷
    public void afterEach(){
        repository.clearStore();
        //test는 순서와 의존관계 상관없이 테스트가 되야한다.
        //그러기위해선 하나의 태스트가 끝나고나서 저장소나 공용데이터를 지워줘야한다.
        //TTD- > TEST를 먼저 만들고 구현클래스를 만들어서 돌려보는것
        //여긴 구현클래스를먼저만드고 잘돌아가는지 테스트 를 만든것이다.
        //테스트가 하나면 모르지만 수십 수백개일 경우는 어떻게하는가?
        //상위 폴더에서 TEST빌드하거나 gradle.test 를 하면된다.
    }


    @Test
    public void save(){
        Member member =  new Member();

        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = "+(result== member));
        //sysout보다는 Assetions 로 해서 알아보자.
        //같으면 아래에 save가체크된 체로 나온다
        //만약 다르게 되면 빨간줄로 로그가 남는다 expected ,actual로
        //Assertions.assertEquals(result,member); //org.junit.jupiter.api.Assertions;
        assertThat(member).isEqualTo(result);//import org.assertj.core.api.Assertions;좀더 편하다.
        //result ==true라는 결과값이 나온다.
        //optional 반환 타입을 get으로 꺼낼수 있음 근데 좋은 방법은아니지만 테스트케이스에서 해도된다.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //rename shift +F6
        member2.setName("spring2");
        repository.save(member2);

        //Member result = repository.findByName("spring1").get();
        Member result = repository.findByName("spring2").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //spring1 spring2가 저장되어있기때문에
        //test를 하나 끝나면 data를 clear시켜놔야함.
        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
