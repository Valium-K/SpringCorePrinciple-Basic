문제점
=====
### 할인 정책을 변경하려면 클라이언트인 `OrderServiceImpl`을 수정해야한다.    
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

### 해결 방안
* **구현객체를 생성** 하고, **연결** 하는 책임을 갖는 별도의 설정 클래스를 만든다.

```java
// 객체의 생성과 연결만을 담당하는 설정 클래스
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
```

* 의존관계에 대한 고민은 `AppConfig`가 맡고 `*Service 클래스`들은 수행에만 집중하면 된다.

[기존 서비스의 문제점](https://www.inflearn.com/course/스프링-핵심-원리-기본편/lecture/55344)   
[변경 후의 구성](https://www.inflearn.com/course/스프링-핵심-원리-기본편/lecture/55346)

