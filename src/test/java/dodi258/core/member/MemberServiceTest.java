package dodi258.core.member;

//import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member("memberA", Grade.BASIC);
        // when
        memberService.join(member);
        Member addedMember = memberService.findMemberById(1L);
        // then
        Assertions.assertThat(member).isEqualTo(addedMember);
    }
}