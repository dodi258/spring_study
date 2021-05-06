package dodi258.core.discount;

import dodi258.core.member.Member;

public class FixedDiscountPolicy implements DiscountPolicy{

    @Override
    public double discount(Member member, int price) {
        return member.getGrade().getFixedDiscountAmount();
    }
}
