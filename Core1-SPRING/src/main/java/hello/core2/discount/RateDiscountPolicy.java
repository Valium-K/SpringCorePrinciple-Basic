package hello.core2.discount;

import hello.core2.member.Grade;
import hello.core2.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private float discountPercent = 0.1f;



    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return (int) (price * discountPercent);
        else
            return 0;
    }
}
