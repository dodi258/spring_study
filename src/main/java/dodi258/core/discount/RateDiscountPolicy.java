package dodi258.core.discount;

import dodi258.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public double discount(Member member, int price) {
        return price * member.getGrade().getRateDiscountAmount();
    }
}
