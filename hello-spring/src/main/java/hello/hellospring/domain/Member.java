package hello.hellospring.domain;

import javax.persistence.*;


//jpa 를 사용할려고하면  entity를 매핑해야한다.
/*라이브러리가 다받아지고나면 hibernate와 jpa가 external libary에 들어와야한다.
-jpa 는 인터페이스만 제공하는거고 구현체로 hibernate 등 구현 기술들이 있는데 이걸 사용하는거다.

-jpa는 java진행의 표준 인터페이스고 구현은 여러업체들이 하는것이다.

- ORM(OBJECT 관계형데이터베이스를 MAPPING하는 기술)


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.id;

 */

@Entity //jpa가 관리하는 entity가 된다.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Id -> 얘는 pk야 @GeneratedValue() 쿼리를 날리면 db가 자동으로 id를 생성해주는것을 identity 전략 이라고 한다.
    private Long id;

   // @Column(name = "username") 어노테이션을 가지고 db랑 매핑을 한다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
