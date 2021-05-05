package dodi258.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.add(member);
    }

    @Override
    public Member findMemberById(long memberId) {
        return memberRepository.findById(memberId).orElse(new Member());
    }
}
