package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //MemoryMemberRepository memberRepository =  new MemoryMemberRepository();
    //다른객체를 또선언하고 있음 뭔가 애매함. 두개를 쓸이유강벗는데 같은걸 쓰는게 맞음.
    //new 로 다른객체를 생성하면 다른인스턴스라서 내용물이 달라질 우려가 있기때문에..

    @BeforeEach//메서드 동작하기 전에
    public void beforEach(){

        memberRepository  = new MemoryMemberRepository();
        memberService =  new MemberService(memberRepository);

        /*1 MemoryMemberRepository 만든다
            2. 만든 repository를 service생성시 넣어준다.
            3. 같은  memoryRepository 를 사용하게된다.
            4. service 입장에서는 직접 new를 하지않고 외부에서 넣어준다
            5. 이게바로 dependecy injection이라고 한다.(생성자 주입)
        *
        */
    }

    @AfterEach //메서드가 실행이 끝날때마다 어떤동작이 실행하게 하는것 callback,이랑 비슷
    public void afterEach(){
        memberRepository.clearStore();

    }

    @Test
    void 회원가입() { //test일경우 과감하게 한글로 해도됨.
        //이런식으로 작성하자.
        //given 이러한 상황이주어져서(이런 데이터 기반)
        Member member = new Member();
        member.setName("hello");


        //when 이런걸실행했을때(실행)
        Long saveId =  memberService.join(member);

        //then 이런게 나와야해(검증)
        Member findMember = memberService.findOne(saveId).get(); //ctrl alt v
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //spring spring 이름이 똑같을때
        //when
        memberService.join(member1);
       // assertThrows(IllegalStateException.class,()-> memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 메시지입니다.");
        //IllegalStateException.class 이 예외
        // ()-> 발생시 memberservice join의 예외가 발생하여야한다.



/*        try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then

    }

    @Test
    void findOne() {
    }
}