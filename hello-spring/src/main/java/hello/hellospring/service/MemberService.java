package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/*
* spring bean을등록하는 2가지 방법
*
* 1. component scane
*  @Controller, @Service, @Repository
*
* 2. 자바 코드로 직접 스프링 빈 등록하기
* */
@Service
public class MemberService {
    //class 선택후 ctrl_shift_t -> test class 생성
    // private final MemberRepository memberRepository =  new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //직접 new에서 생성하지 않고 외부에서 넣어주도록 변경 

    /*
    회원 가입
    * */
    @Transactional
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
     /*   Optional<Member> result = memberRepository.findByName(member.getName());
        //result.get(); 처럼 바로꺼내는것보단 optinal로 감싸서 씀

        result.ifPresent(m ->{
            throw new IllegalStateException("ㅇ미ㅣ 존재하는 회원입니다.");
        });*/

        //optional을 result.바로 반환하는 것은 별로기때문에.
       // long start  = System.currentTimeMillis();
       // try {


            validateDuplicateMember(member);//중복회원검색
  /*      memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); ctrl+alt +m으로 바로 메서드로 뽑아서 사용 ctrl_alt_shit_t refactoring관련 검색*/
            memberRepository.save(member);
            return member.getId();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish -start;
//            System.out.println("join = "+ timeMs +"ms");
//        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    전체 회원 조회
    */
    public List<Member> findMembers(){
//        long start  = System.currentTimeMillis();
//        try {
            return memberRepository.findAll();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish -start;
//            System.out.println("findMembers "+timeMs +"ms");
//        }
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);

    }

    //참고 서비스에서 메서드 네이밍을 비지니스 로직에 맞게 하고 repostiotry 쪽에서는 데이터 주입에 맞게 정한다.
}
