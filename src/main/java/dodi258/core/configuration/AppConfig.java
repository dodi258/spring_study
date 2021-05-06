package dodi258.core.configuration;

import dodi258.core.discount.FixedDiscountPolicy;
import dodi258.core.member.MemberService;
import dodi258.core.member.MemberServiceImpl;
import dodi258.core.member.MemoryMemberRepository;
import dodi258.core.order.OrderService;
import dodi258.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemberServiceImpl(new MemoryMemberRepository()),
                new FixedDiscountPolicy());
    }
}
