package dodi258.core.configuration;

import dodi258.core.discount.DiscountPolicy;
import dodi258.core.discount.FixedDiscountPolicy;
import dodi258.core.member.MemberRepository;
import dodi258.core.member.MemberService;
import dodi258.core.member.MemberServiceImpl;
import dodi258.core.member.MemoryMemberRepository;
import dodi258.core.order.OrderService;
import dodi258.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberService(),
                discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixedDiscountPolicy();
//        return new RateDiscountPolicy();

    }

}


/*
    AppConfig
    - 구성 정보에서 역할과 구현을 명확하게 분리
    - 역할이 잘 드러남
    - 중복 제거

    - 구현체를 변경해도, AppConfig 가 있는 구성 영역만 변경하면 됨,
      사용 영역은 변경할 필요가 없음.
 */