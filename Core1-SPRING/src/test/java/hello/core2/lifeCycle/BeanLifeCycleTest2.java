package hello.core2.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest2 {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient2 client = ac.getBean(NetworkClient2.class);
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

        @Bean
        public NetworkClient2 networkClient2() {
            NetworkClient2 networkClient2 = new NetworkClient2();
            networkClient2.setUrl("https://www.google.com");

            return networkClient2;
        }
    }
}
