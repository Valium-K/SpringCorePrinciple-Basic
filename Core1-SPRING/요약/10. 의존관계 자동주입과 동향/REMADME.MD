의존관계 자동 주입
===============

## 의존관계 주입 방법
### 생성자 주입
* 생성자 호출 시점에 주입하여 1번만 주입됨
* **불변, 필수(setter 없이 private final)** 의존관계시 사용
* **생성자가 1개만 있으면 `@Autowired`를 생략해도 된다.**

### setter 주입
* 스프링 컨테이너 생성단계 후 의존관계 주입 단계 때 주입된다.
* **선택, 변경가능**의존관계시 사용
    ```java
    public class SomeServiceImpl implements SomeService {
        private repository Repository;
        
        // 생성자는 없음
        
        @Autowired
        public void setRepository(Repository repository) {
            this.repository = repository;
        }
    }
    ```

## 필드 주입
* 코드는 간결하지만 단위 테스트(순수 Java 테스트)는 힘들어진다.
  * 의존관계를 DI 프레임워크가 관리하기에 통합 테스트로 테스트를 해야하며,    
    그마저도 의존객체를 바꿔 테스트를 한다면 직접 코드를 수정하거나   
    setter나 생성자를 만들어 DI 해야하기에 필드주입의 의미가 없어진다.
    
* 애플리케이션의 실제 코드와 관계없는 테스트 시, 스프링 컨테이서에서 테스트시 에는 사용해도 문제없다.
  * `@SpringBootTest`: 테스트 클래스에 붙이면 빈을 스프링 컨테이너에 올려 바로 테스트를 한다. (통합 테스트)
    ```java
    @Component
    public class SomeServiceImpl implements SomeService {
   
        @Autowired
        private repository Repository;
        
        // 생성자 없음
        
        // setter 없음
    }
    ```

## 일반 메서드 주입
* 잘 사용하지 않음

---------------------------

의존관계 주입 동향
================
## 생성자 주입을 사용한다. 이것은 동향이며 권장이다.
* 예시: 
  `A` 클래스의 단위 테스트시 `B, C, D`라는 의존관계의 객체가 필요 한 경우에    
  해당 객체의 `B', C', D'` 라는 더미 객체가 필요하다.(`A` 테스트가 핀포인트이기에.)   
  
    * 필드 주입: `B, C, D` 대신 `B', C', D'`로 코드를 수정해야함.
    * setter 주입: setter로 `B', C', D'`를 주입하면 되기에 문제는 없지만,    
      실수로 `B', C', D'`로 교체하지 않아도 컴파일 오류가 뜨지 않아    
      런타임 오류를 확인 한 이후 다시 고쳐야해 일을 두번 할 수있다.
      
* 생성자 주입에서만 final 키워드를 사용 가능하다.
    * 불변, 필수를 강제하며 DI를 깜빡하고 안하는 실수를 막아준다.
    
