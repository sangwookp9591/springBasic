package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloContoller {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }
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