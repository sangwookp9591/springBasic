package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //spring이 뜰때 Configuration읽어오고 아래 로직을 호출해서 spring bean에 등록한다.
public class SpringConfig {
    //MemberService와 MemberRepository를 스프링이 올라올때 memberService가 bean에 등록되면서 repository도 bean에 등록
    //Controller는 어차피 스프링이 관리하는것이기 때문에 component scan을 통해 이용
    @Bean //Spring bean을 등록할거야
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();

    }
}
