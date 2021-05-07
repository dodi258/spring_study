package dodi258.core.member;

import dodi258.core.configuration.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class MemberServiceTest {

    // Class to test
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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