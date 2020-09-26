package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //기능은없지마 spring이뜰때 sprinmg container라는 통이있는데. 거기에 어노테이션이 있으면 생성해서 담고 관리를 한다.
public class MemberController {//spring container에서 bean이 관리된다.

    private MemberService memberService;

    //@Autowired private MemberService memberService; 필드주입이다.
    //필드주입은 별로 안좋다 바꿀수있는방법이 없음 스프링뜰때만 넣어주고 중간에 바꿀수 있는 방법이없다.


    /*memberController를  spring 컨터에너가 뜰때 생성을하는데 그때 생성자를 호출하는데
  생성자에 autowired라고 되어있으면  memberservice를 spring이 spring container에 있는
  memberSerivce를 가져와서 연결시켜 준다.
 */
  @Autowired //생성자 주입(요즘권장하는스타일)!!
  public MemberController( MemberService   memberService) {
        this.memberService = memberService;
  }/*apllication이 조립될때 memberservice한번 딱들어오고 끝난다.
    생성하는 시점에만 딱 넣고 변경을 못하도록 해야한다.
    의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
    */


    /*private final MemberService memberService = new MemberService();
    new로 생성할때의 문제점

    Spring으로 관리를 할때 스프링은 다 spring container에서 등록하겓된다.
    new라고 하면 다른 controller에서도 memeberService를 가져다 쓸 수 있다.
    여러개를 생성할 필요가없고 하나만 생성해서 공용해서 쓰면된다.
    * */


    /* setter 주입 - 생성은 생성대로 되고,  setter는 나중에 호출이되어 memberService가 들어온다.
   단점 ! -누군가가 memberController를 호출했을때 이메서드 자체가 public으로 되어있어야함.
   public 이기때문에 노출되어 있어 위험성*/
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService =  memberService;
    }
}

/*참고: 스프링을 스프링 컨테이텅 스프링 빈을 등록할때, 기본적으로 싱글톤으로 등록한다
*(유일하게 하나만 등록해서 공유한다)따라서 같은 스프링 빈이면 모두 같은 인스턴스다. 설정으로 싱글톤이 아니게
* 설정할 수 았지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.'
*
* 참고: 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
* 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링빈으로 등록한다.
*
*
*
* spring(spring container)에 등록되고 스프링에서 관리를 해야야 스프링이 autowired를 적용할 수 있음.
* 즉! spring bean으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.
*
*/
