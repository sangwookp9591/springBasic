package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
	// SpringBootApplication 이렇게하면 띄우면서  tomcat webserver 내장하고 있어서
	// 자체적으로 띄우면서 스프링부타가 같이 올라간다.
	//setting에서 gradle검색 
	//build and run using Run test unsing을 intellij 로 바꿔줌 그래야
	//gradle로 인한 느려짐 현상을 해결할 수 있다.
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
