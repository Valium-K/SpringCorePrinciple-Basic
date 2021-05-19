컴포넌트 스캔과 의존관계 자동 주입
============================
* 지금까지는 설정 정보에 직접 등록할 스프링 빈을 나열했지만    
    설정 정보를 기술하지 않고 자동으로 스프링 빈을 등록하는 `ComponentScan` 기능이 있다.
* `@Component`가 붙은 클래스를 찾아 스프링 빈으로 자동 등록해준다.
    * 이 때 빈을 등록만 하면 의존관계 설정을 할 수 없으니    
      의존관계를 형성하는 생성자에 `@Autowired`를 붙여준다.
      
```java
// 컴포넌트 스캔을 위한 @Component
// @Compoent가 붙은 클래스가 스프링 빈으로 등록된다.
// 빈 이름은 클래스 이름을 `camelCase`로(camelCase를 지키지 않았다면 앞 글자만 소문자로 바꿔치기 함) 등록한다.
@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 의존관계 자동주입을 위한 @Autowired
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
}
```

### 참고
* `@Autowired`의 기본 전략은 같은 타입이다.
    > 기존의 ac.getBean(클래스명.class)방식과 같다.
* `@Component`로 빈을 등록하고 각각의 빈의 의존관계를 `@Autowired`로 연결한다 생각하면 된다.
* 빈 이름을 직접 정하고 싶다면 `@Component("빈 이름")`과 같이 한다.
