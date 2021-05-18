싱글톤 컨테이너
============

## 싱글톤
* 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 디자인 패턴
    ```java
    // 가장 단순한 싱글톤 생성 예제
    public class SingletonService {
    
        private static final SingletonService instance = new SingletonService();
        private SingletonService() {}
    
        public static SingletonService getInstance() {
            return instance;
        }
    
        public void logic() {
            System.out.println("싱글톤 객체 로직 호출");
        }
    }
    ```
* 단점
    * 싱글톤패턴을 구현하는 코드 자체가 많이 들어간다.
    * 의존관계상 클라이언트가 구체클래스에 의존한다.  
    * DIP를 위반한다. 
    * 클라이언트가 구체클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
    * 테스트하기 어렵다. 내부속성을 변경 하거나 초기화하기 어렵다.
    * private 생성자로 자식클래스를 만들기 어렵다. 결론적으로 유연성이 떨어진다.
    
## 싱글톤 컨테이너
* 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체인스턴스를 싱글톤으로 관리한다.


## 싱글톤 방식의 주의점
* 무상태(stateless)로 설계해야한다
  * 특정 클라이언트에 의존적인 필드가 있으면 안된다.
  * 특정 클라이언트가 값을 변경 할 수 있는 필드가 있으면 안된다.
  * 가급적 읽기만 가능 해야한다.
  * 필드대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야한다.

### 문제점 예시  
* [StatefulService](/Core1-SPRING/src/test/java/hello/core2/singleton/StatefulService.java)   
* [StatefulServiceTest](/Core1-SPRING/src/test/java/hello/core2/singleton/StatefulServiceTest.java)
