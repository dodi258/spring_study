package dodi258.core.order;

import dodi258.core.configuration.AppConfig;
import dodi258.core.member.Grade;
import dodi258.core.member.Member;
import dodi258.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OrderServiceImplTest {

    // Class to test
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        // when
        Order newOrder = orderService.createOrder(memberId, "itemA", 10000);
        // then
        Assertions.assertThat(newOrder.getDiscountPrice()).isEqualTo(1000);
    }

}