package hello.core2.autowired;

import hello.core2.AutoAppConfig;
import hello.core2.discount.DiscountPolicy;
import hello.core2.member.Grade;
import hello.core2.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1l, "userA", Grade.VIP);

        // DiscountService가 DiscountPolicy를 의존하면서 discountCode로 선택 할 수 있기에 OCP를 잘 기킨다.
        // 등록된 빈에서 rateDiscountPolicy를 선택
        int discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(2000);

        // 등록된 빈에서 fixDiscountPolicy를 선택
        int fixDiscountPolicy = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(fixDiscountPolicy).isEqualTo(1000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            // System.out.println("policyMap = " + policyMap);
            policyMap.forEach((k, v) -> System.out.println(k));
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);

        }
    }
}
