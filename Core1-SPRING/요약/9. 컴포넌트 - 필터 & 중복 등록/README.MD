컴포넌트 스캔 - 필터 & 중복 등록
=================

## 필터
[필터 예제](Core1-SPRING/src/test/java/hello/core2/scan/filter)


## 중복 등록과 충돌

자동 빈 등록 vs 수동 빈 등록
최근 스프링 부트는 오버라이딩이아닌 오류를 기본값으로 사용한다.


Description:    

The bean 'memberRepository', defined in class path resource [hello/core2/AutoAppConfig.class], could not be registered. A bean with that name has already been defined in class path resource [hello/core2/AppConfig.class] and overriding is disabled.   

Action:    

Consider renaming one of the beans or enabling overriding by setting `spring.main.allow-bean-definition-overriding=true`    

> `application.properties`에 `spring.main.allow-bean-definition-overriding=true`를 사용해 오버라이딩으로 바꿀 수 있다.