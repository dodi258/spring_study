package dodi258.core.discount;

import dodi258.core.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */

    double discount(Member member, int price);
}
