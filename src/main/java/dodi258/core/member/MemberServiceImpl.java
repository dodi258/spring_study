package dodi258.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /*
        이전에 AppConfig 에서는 @Bean 으로 직접 설정 정보를 작성했고, 의존 관계도 직접 명시했다.
        이제는 이런 설정 정보 자체가 없기 때문에, 의존 관계 주입도 이 클래스 안에서 해결해야한다.
     */
    @Autowired //== ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.add(member);
    }

    @Override
    public Member findMemberById(long memberId) {
        return memberRepository.findById(memberId).orElse(new Member());
    }
}
