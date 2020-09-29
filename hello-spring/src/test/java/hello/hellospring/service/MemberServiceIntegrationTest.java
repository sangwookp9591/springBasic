package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //spring containmer와 test를 함께 실행한다.
@Transactional
        /*TEST캐이스에 달면 테스트를 할때 TEST 시작전에 TRACTION을 먼저 실행하고
        QEURY를 실행하고 test가 끝난 후에 ROLLBACK을 해준다. DB에 넣었던 데이터가 반영이안되고 지워진다.
        -db에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
        */
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    /*기존코드들은 생성자를 사용하는게 좋은데
    TEST같은경우는 다른곳에서 내가 가져다 쓸게 아니기 때문에
    필드기반으로 Autowirde한다.
    * */

    /*
    *DB에 INSERT 쿼리든 다날린다음에 ROLLBACK을 한다
    *ROLLBACK시 데이터가 다 없어지기 때문에 이것으로 검증을 한다.
    * 기존처럼 데이터를 지우는 코드를 따로 넣지 않아도된다 !!.
    *    @AfterEach //메서드가 실행이 끝날때마다 어떤동작이 실행하게 하는것 callback,이랑 비슷
       public void afterEach(){
        memberRepository.clearStore();
    }
    * */
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}