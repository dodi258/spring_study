package dodi258.core.order;

import dodi258.core.discount.DiscountPolicy;
import dodi258.core.discount.FixedDiscountPolicy;
import dodi258.core.member.Member;
import dodi258.core.member.MemberService;
import dodi258.core.member.MemberServiceImpl;

public class OrderServiceImpl implements OrderService{

    private final MemberService memberService;
    private final DiscountPolicy discountPolicy;

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
