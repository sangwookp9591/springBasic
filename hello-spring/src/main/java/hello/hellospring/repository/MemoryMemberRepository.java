package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store =  new HashMap<>();
    private static long sequence = 8L; //sequence 는 0,1,2이런식으로 key값을 생성해주는 애 실무에서는 long보다는 동시성문제를 고려해서 해야함/
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //client에서 뭘할수있다?
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
                //roof돌리면서 .filter member의 getName이 파라미터에서 넘어온 name이랑 같은지 확인
                //같은경우에만 필터링하고 찾으면 반환.

        //findAny는 하나라도 찾는거고 결과가 Optional로 반환되는 것이다. Map에서 돌면서 한찾아지면 그걸 반환한다.


    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//store.values() 는 store에 있는 member이다.
    }
}
