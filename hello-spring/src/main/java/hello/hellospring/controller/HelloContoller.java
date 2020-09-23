package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
