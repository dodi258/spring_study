package dodi258.core.discount;

import dodi258.core.configuration.AppConfig;
import dodi258.core.member.Grade;
import dodi258.core.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    // Class to test
    DiscountPolicy discountPolicy;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        discountPolicy = appConfig.discountPolicy();
    }

    @Test
    @DisplayName("VIP 는 10% 할인이 적용 되어야 한다.")
    void discount_vip() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // when
        double discount = discountPolicy.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP 가 아니면 할인이 적용되지 않아야 한다.")
    void not_discount_noneVip() {
        // given
        Member member = new Member(1L, "memberNoneVIP", Grade.BASIC);
        // when
        double discount = discountPolicy.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(0);
    }

}