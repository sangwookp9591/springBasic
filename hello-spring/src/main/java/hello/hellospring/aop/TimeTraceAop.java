package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
*
* AOP: Aspect Oriented Programming
공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리
*
* */
@Aspect//이걸적어줘야 AOP로 쓸 수 있다.
//@Component //Spring bean으로 등록을 해줘야함. @Component하면 자동으로 ComponentScan이 된다.
//하지만 SpringConfig에서 SpringBean에 등록해서 쓰는걸 더 선호한다.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")//공통관심사항을 어디에다 적용할 것인가? targeting
    //hello.hellospring  패키기명 , .* 클래스명 ,  (파라미터타입) 등 원하는 조건을 넣을 수 있다 위는 패키지 하위에 다적용해 ~
    //* hello.hellospring.service..*(..))" 서비스 하위에만 동작하게 할 수 있다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.toString());
        try{
            return joinPoint.proceed(); //.proceed하면 다음 메서드로 진행
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+ joinPoint.toString()+" TIMEMS: "+timeMs);
        }


    }
    /*호출이 될 때마다 인터셉터가 딱딱 걸리면서 joinPoint(여기 파라미터가 많이있음 어느 타겟에서 호출하는지 아규먼트가 뭔지? 등등 ..)
    *joinPoint로 조작을 할 수 있고 ,.다음메서드로 넘어갈 수도 있지만
    * 이런조건일때는 넘어가지말라는 것으로 작성할 수도 있다.
    *
    * 중간에 인터셉팅해서 풀수 있는 기술이 AOP이다.
    *  */


    /*
    * AOP가 동작하는 여러가지 방법
    *
    * 스프링 컨테이너
    * helloController  -> memberService
    * -의존관계에 의해서 호출함.
    *
    * AOP적용후 의존관계
    * helloController  -> memberService(가짜 프록시)  -> memberService
    * - Spring은 AOP가 있으면 가짜  memberService(프록시)를 만들어 낸다.
    * - spring container는  동작할때 스프링빈을 등록할떄 진짜 Spring bean을 말고
    *   가짜 스프링빈을 앞에 새워 놓는다. 그리고 가짜 spring bean이 끝나면
    *   joinPoint.proceed()하면 내부적으로 이것저것 타서 그때 진짜를 호출해준다.
    *   helloController가 호출하는건 진짜  service가 아니라 프록시라는 기술로 발생하는 가짜 serivce이다
    *   프록시인지 확인하는 방법은 memberService가 injection될떄 (생성자 주입) memberService.getClass()로 찍어보면 된다.
    *   MemberService$$EnhanceBySpringCGLIB$$de89088이라고 나온다.
    *
    * */
}
