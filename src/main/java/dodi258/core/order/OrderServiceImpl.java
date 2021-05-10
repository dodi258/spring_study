package dodi258.core.order;

import dodi258.core.discount.DiscountPolicy;
import dodi258.core.member.Member;
import dodi258.core.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberService memberService;
    private final DiscountPolicy discountPolicy;

    /*
        @Autowired 를 사용하면 생성자에서 여러 의존 관계도 한번에 주입받을 수 있다.
     */
    @Autowired
    public OrderServiceImpl(MemberService memberService, DiscountPolicy discountPolicy) {
        this.memberService = memberService;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(long memberId, String itemName, int itemPrice) {
        Member member = memberService.findMemberById(memberId);
        double discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
