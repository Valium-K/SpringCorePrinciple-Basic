예제에서의 SOLID 원칙
==================

### SRP 단일책임원칙 한 클래스는 하나의 책임 만 가져야한다.
* 클라이언트객체는 직접 구현객체를 생성하고, 연결하고, 실행하는 다양한 책임을 가지고있음
* SRP 단일책임원칙을 따르면서 관심사를 분리함
* 구현객체를 생성하고 연결하는 책임은 AppConfig가 담당
* 클라이언트객체는 실행하는 책임만 담당

### DIP 의존 관계 역전 원칙
프로그래머는 “추상화에 의존해야지, 구체화에 의존하면 안된다.”   
의존성 주입은 이 원칙을 따르는 방법 중 하나다.

### OCP  
소프트웨어요소는 확장에는 열려있으나 변경에는 닫혀있어야한다

### IoC, DI(Dependency Injection) 의존관계주입
`OrderServiceImpl`은 `DiscountPolicy` 인터페이스에 의존한다.   
실제어떤구현객체가사용될지는모른다.  
> 의존관계는 정적인클래스 의존관계와, 실행시점에 결정되는 동적인객체(인스턴스)  
> 의존관계 둘을 분리해서 생각해야한다  

* 정적 클래스 의존관계클래스가 사용하는 import 코드만보고 의존관계를 쉽게 판단 할 수 있다.
* 객체 클래스 다이어그램으로 동적 분석이 가능
    * 동적으로 연결이 되게 하는것을 DI라 보면 된다.
    
> 의존관계주입을 사용하면 정적인클래스 의존관계를 변경하지않고,
> 동적인객체 인스턴스 의존관계를 쉽게 변경할 수 있다

* DI container / IoC container / AppConfig Assembler / Factory 다 같은 말이다.
