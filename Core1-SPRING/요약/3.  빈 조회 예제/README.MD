빈 조회 예제
==========
> ApplicationContainer에서 getBean을 할 일은 많이 없다. 굳이 한다면  
    * 순수 자바 클래스에서 스프링 컨테이너를 조회 할 때    
    * 특정 부모타입으로 조회시 자식 타입이 얼마나 있는가

### 기본
* [ApplicationContextBasicFindTest](/Core1-SPRING/src/test/java/hello/core2/beanfind/ApplicationContextBasicFIndTest.java)  
* [ApplicationContextInfoTest](/Core1-SPRING/src/test/java/hello/core2/beanfind/ApplicationContextInfoTest.java)  

### 타입으로 조회 + 동일 타입 처리
* [ApplicationContextBasicFIndTest](/Core1-SPRING/src/test/java/hello/core2/beanfind/ApplicationContextSameBeanFindTest.java)

### 상속관계의 빈 조회
> **중요**: 부모타입 조회 -> 모든 자식 타입이 조회됨

* [ApplicationContextExtendsFindTest](/Core1-SPRING/src/test/java/hello/core2/beanfind/ApplicationContextExtendsFindTest.java)