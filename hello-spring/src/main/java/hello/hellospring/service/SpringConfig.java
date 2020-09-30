package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration //spring이 뜰때 Configuration읽어오고 아래 로직을 호출해서 spring bean에 등록한다.
public class SpringConfig {

    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    private DataSource dataSource; //db에 연결할수있는 정보를 만들어주고

   /* @Autowired
   public SpringConfig(DataSource dataSource) { //의존성 주입을 한다.
        this.dataSource = dataSource;
    }

    */

    //MemberService와 MemberRepository를 스프링이 올라올때 memberService가 bean에 등록되면서 repository도 bean에 등록
    //Controller는 어차피 스프링이 관리하는것이기 때문에 component scan을 통해 이용
    @Bean //Spring bean을 등록할거야
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();

        //return new JdbcMemberRepository(dataSource);

        //return new JdbcTemplateMemberRepository(dataSource);

        return new JpaMemberRepository(em);


    }
    /*
    * 여기서 다형성을 알수 있다 인터페이스를 두고 구현체를 바꿔끼기를 할 수 있따.
    *
    * 이걸 스프링이 굉장히 편리하게 구현할 수 있게 해준다.
    *
    * 기존의 코드는 하나도 손대지않고  application 을 설정하는 코드  (application을 조립하는 코드만) 딱소내고
    * 나머지; 실제 application 관련 코드는 손댈 필요가 없다.
    *
    *
    * */
}
