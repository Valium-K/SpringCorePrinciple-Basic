package hello.core2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 컴포넌트 스캔 사용 - @Component가 붙은 클래스를 찾아 스프링 빈을 자동으로 등록해준다.
        // ComponentScan 검증을 위해 AppConfig, TestConfig 등에 붙은 @Configuration을 잠시 필터링 한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
