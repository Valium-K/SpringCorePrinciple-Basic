package hello.core2.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    @DisplayName("공유필드로 인한 값의 이상")
    void statefulServiceSingleton() throws InterruptedException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // 스프링 컨테이너에 관리되는 Bean인 StatefulService는 싱글톤이라 사실 같은 객체
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB: B사용자 20000원 주문
        statefulService2.order("UserB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();

        Assertions.assertThat(price).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
