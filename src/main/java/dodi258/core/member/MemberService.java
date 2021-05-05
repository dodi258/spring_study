package dodi258.core.member;

public interface MemberService {

    void join(Member member);

    Member findMemberById(long memberId);

}
