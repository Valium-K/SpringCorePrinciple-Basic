애노테이션 팁
===========

기존에 `@Qualifier("MainDiscountPolicy")` 같은 애노테이션의 String은
컴파일 타임에 체크가 되지 않는다. 이를 해결하기위해 애노테이션을 만들어 볼 수 있다.


## 해결 전
### RateDiscountPolicy
```java
@Component
@Qualifier("mainDIscountPolicy") // Discount가 아닌 DIscount라 오타를 낸 상황 - 런타임 에러
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
```


----------------------------------

## 해결 후
### MainDiscountPolicy 애노테이션
```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")    // @Qualifier를 감싸는 애노테이션을 만든 것 이다.
public @interface MainDiscountPolicy { }
```
> `@Target`, `@Retention`등 애노테이션 을 만들 때 필요한 요소는    
> `@Qualifier`같이 이미 있는 애노테이션에 `Ctrl + B`로 이동 후 코드를 긁어오면 편하다.

###  RateDiscountPolicy
```java
@Component
@MainDiscountPolicy // 자동완성으로 오타를 내기 어려우며 낸다해도 컴파일 타임에 잡아낼 수 있다.
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
```

> 이 해결 방안은 **스프링 내에서만 동작한다.**     
> 단순 java에서 애노테이션은 상속관계가 없기 때문이다.