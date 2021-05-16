문제점
=====
할인 정책을 변경하려면 클라이언트인 `OrderServiceImpl`을 수정해야한다.    
```java
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); 
    
    // ...
}
```
* 위 상황은 OCP와 DIP를 위배한다.
> * DIP: 객체 안에서 직접 `FixDiscountPolicy` 혹은 `RateDiscountPolicy`를 생성하기에   
> 실은 `DiscountPolicy`인터페이스를 의존하고 해당 구현체 또한 의존하는 상황이다.    
> * OCP: 기능을 확장해 변경하면, 클라이언트 코드(`OrderServiceImpl`의 변경이 필요하다.




