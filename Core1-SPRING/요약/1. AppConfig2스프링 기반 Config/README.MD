 AppConfig to 스프링 기반 Config
 =============================

## 기존: AppConfig를 직접 찾아옴

### AppConfig
```java
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

```
### 사용시
```java
AppConfig appConfig = new AppConfig();
MemberService memberService = appConfig.memberService();
```

## 이후: 스프링 컨테이너를 통해서 찾아옴
```java
// 스프링 컨테이너는 `@Configuration`이 붙은 설정파일을 사용한다.
@Configuration
public class AppConfig {

 // @Bean: 스프링 컨테이너에 등록
 @Bean
 public MemberService memberService() {
  return new MemberServiceImpl(memberRepository());
 }

 @Bean
 public MemberRepository memberRepository() {
  return new MemoryMemberRepository();
 }

 @Bean
 public OrderService orderService() {
  return new OrderServiceImpl(memberRepository(), discountPolicy());
 }

 @Bean
 public DiscountPolicy discountPolicy() {
  // return new FixDiscountPolicy();
  return new RateDiscountPolicy();
 }
}
```
### 사용시
```java
// 스프링 컨테이너 == ApplicationContext
// AppConfig의 @Bean에 대해 관리를 할 것이다 - AnnotationConfigApplicationContext 사용
// AnnotationConfigApplicationContext에 AppConfig 클래스 자체를 넘겨준다.
ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

// 인자 - name: 찾을 @Bean이 달린 메서드 이름, Class: 해당 타입
MemberService memberService = ac.getBean("memberService", MemberService.class);
```

## 정리
* `ApplicationContext`를 스프링 컨테이너라 한다.
* 기존에는 개발자가 `AppConfig`를 사용해서 직접 객체를 생성하고 DI를했지만,   
    이제부터는 스프링컨테이너를 통해서 사용한다.
* 스프링컨테이너는 `@Configuration`이 붙은 `AppConfig`를 설정(구성) 정보로사용한다.    
      여기서 `@Bean`이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링컨 테이너에 등록한다.    
      이렇게 스프링컨테이너에 등록된객체를 스프링빈이라한다.
* 스프링 빈은 `@Bean`이 붙은 메서드의 명을 스프링빈의 이름으로 사용한다. 
* 이전에는 개발자가 필요한 객체를 `AppConfig`를 사용해서 직접 조회 했지만,  
  이제 부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾아야한다. 
* 스프링빈은 `applicationContext.getBean()`메서드를 사용해서찾을수있다.
* 기존에는 개발자가 직접 자바코드로 모든것을 했다면 이제부터는    
              스프링 컨테이너에 객체를 스프링 빈으로 등록하고,     
              스프링 컨테이너에서 스프링빈을 찾아서 사용 하도록 변경되었다.
  