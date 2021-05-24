package hello.core2.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest1 {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient1 client = ac.getBean(NetworkClient1.class);
        ac.close(); // 종료

        /* sout 순서
        생성자 호출, url = null
        NetworkClient.init()
        connect: https://www.google.com
        call: https://www.google.com message = 초기화 연결 메시지
        ... Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@199b690d ...
        NetworkClient.destroy()
        disconnect: https://www.google.com
         */

    }

    @Configuration
    static class LifeCycleConfig {

        // 참고로 destroyMethod는 추론기능이 있어서 빈 메서드 명 중 close, shutdown이 있다면
        // 자동으로 호출한다. 즉 @Bean(initMethod = "init")만 적어도 메서드명이 close라면 동일하게 동작한다.
        @Bean(initMethod = "init")
        public NetworkClient1 networkClient1() {
            NetworkClient1 networkClient1 = new NetworkClient1();
            networkClient1.setUrl("https://www.google.com");

            return networkClient1;
        }
    }
}
