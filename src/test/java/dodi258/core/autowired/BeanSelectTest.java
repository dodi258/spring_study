package dodi258.core.autowired;

import dodi258.core.configuration.AutoAppConfig;
import dodi258.core.discount.DiscountPolicy;
import dodi258.core.member.Grade;
import dodi258.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BeanSelectTest {
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        double discountPrice = discountService.discount(member, 10000, "fixedDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> polices) {
            this.policyMap = policyMap;
            this.policies = polices;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public double discount(Member member,int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}

/**
 * 로직 분석
 * - DiscountService 는 Map 으로 만든 DiscountPolicy 를 주입받는다.
 *   이때 fixDiscountPolicy, rateDiscountPolicy 가 주입된다.
 * - discount() 메서드는 discountCode 로 "fixDicountPolicy"가 넘어오면 map 에서
 *   fixDiscountPolicy 스프링 빈을 찾아서 실행한다. 물론 "rateDiscountPolicy"가 넘어오면
 *   rateDiscountPolicy 스프링 빈을 찾아서 실행한다.
 */
