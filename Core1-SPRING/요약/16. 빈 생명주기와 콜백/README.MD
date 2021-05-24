빈 생명주기
============

## 스프링 빈의 라이프 사이클 - 싱글톤    
스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 ->      
사용 ->     
빈 소멸전 콜백 -> 스프링 컨테이너 소멸 -> 스프링 종료

> 단일 책임 원칙에 따라 객체의 생성과 초기화는 분리해야한다.    
> 그래서 생성자는 new에 집중을 하고 다른 메서드에서 초기화를 하는게 낫다.     
> 단, 객체 내부의 필드 설정정도의 단순한 설정이면 상관없다.


## 스프링에서 지원하는 콜백 3가지
### 인터페이스(InitializingBean, DisposableBean)
  * 단점: 스프링 전용 인터페이스이다. and 스프링 초기에 나와서 `더는 사용하지 않는다.`

### 설정 정보에 초기화 메서드, 종료 메서드 지정
  * 사용법: ```@Bean(initMethod = "init_메서드_이름", destroyMethod = "destory_메서드_이름")```

  * 장점
    * 스프링 빈이 스프링 코드에 의존하지 않는다.
    * 코드가 아닌 설정 정보를 사용하기에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.    
    
  * 예제
    * [Bean: NetworkClient1](/Core1-SPRING/src/test/java/hello/core2/lifeCycle/NetworkClient1.java)    
    * [Test: BeanLifeCycleTest1](/Core1-SPRING/src/test/java/hello/core2/lifeCycle/BeanLifeCycleTest1.java)

### `@PostConstruct`, `@PreDistroy` 애노테이션 지정
  * 사용법
    * `@PostConstruct`, `@PreDestroy`를 init 메서드, destroy 메서드에 각각 적용해준다.
  * 장점
    * 문법이 편하고 `javax.annotation` 패키지라 다른 컨테이너에서도 동작한다.
    * **일반적으로 이 방법을 사용하면 된다.**
  * 단점
    * 외부 라이브러리에 적용을 못 함(외부 코드 수정을 못 하기에). 그래서 위의 @Bean 방식을 사용하면 된다.
  * 예제
    * [Bean: NetworkClient2](/Core1-SPRING/src/test/java/hello/core2/lifeCycle/NetworkClient2.java)
    * [Test: BeanLifeCycleTest2](/Core1-SPRING/src/test/java/hello/core2/lifeCycle/BeanLifeCycleTest2.java)