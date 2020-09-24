package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloContoller {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }
    /*
    *
    * (mac) ./gradlew build

    (window) gradlew build

    서버종료 > Ctrl + C
    *
    * cd build
    * cd libs
    * dir 명령어로  hello-spring-0.0.1-SNAPSHOT.jar 생성황인
    * 서버배포
    *java -jar hello-spring-0.0.1-SNAPSHOT.jar
    * 예전에는 톰캣 서버에설치하고 특정 폴더에서 war파일 넣고 했었는데 그럴 필요가 없음.
    *
    *
    * */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";

    }
    /*
    *
    웹브라우저에서 localhost:8080으로 내장 톰켓서버에 거쳐 helloController에 매핑되있기때문에
    * 매핑해주고  return: hello-tmeplate 과똑같은 이름을 찾는다.
    * viewResolver view를 찾아주고 템플릿 엔진을 처리해준다.
    * templates/hello-template.html 찾고 hello-template.템플릿엔진으로 html로 변환한 html을 반환 * */


    @GetMapping("hello-string")
    @ResponseBody //html에 나오는 body 태그가아니라 http에서 header와 body가 있는데 body부에 이 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //hello spring  이렇게 내려서 소스를 보면 html태그가 없음 데이터만 그대로내리는 방식이다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ //value
        Hello hello =  new Hello();
        hello.setName(name);
        return hello; //객체를 반환하고 @ResponseBody로하면 default로 json으로 결과가 떨어짐

    }
    /*
    *웹브라우저  -> localhost:8080/hello-api -> 내장 톰켓 서버 -> 스프링 컨테이너 helloController  -> @ResponseBody가 있으면 r
    * hello객체를 넘기면 HttpMessageConverter가 동작함 .
    *  단순 문자면 MappingJackson2HttpMessageConverter가 동작하고
    * #여기서 jackson은 무엇이냐? json으로 바꿔주는 라이브러리가 있는데 대표적 두개  jackson, gson두개 가있는데
    * spring은 jackson을 기본으로 채택하고 있다 (범용성과 검증된 라이브러리이기 떄문이다.)
    * json이면 JsonConverter가 동작한다.
    * Json style 로 바꿔 server든 웹 브라우저에 전달한다.
    *
    *참고: 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서
    * HttpMessageConverter가 선택된다.
    *
    * */
    static class Hello{
        private String name; //key
        //getter setter property 접근 방식.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
