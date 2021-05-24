package hello.core2.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        // getBean한 시점에서 초기화를 한다.
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 종료를 해도 destroy() 메서드는 호출되지 않는다.
        ac.close();

        // 클라이언트가 관리해야함으로 prototypeBean1.destroy(); 등을 직접 관리해야한다.
        /*
        find prototypeBean1
        prototypeBean.init      // getBean한 시점에서 초기화를 한다.
        find prototypeBean2
        prototypeBean.init
        prototypeBean1 = hello.core2.scope.PrototypeTest$PrototypeBean@1c48a62e
        prototypeBean2 = hello.core2.scope.PrototypeTest$PrototypeBean@4818e68
        ... Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@e4af34d, ...


        // 종료를 해도 destroy() 메서드는 호출되지 않는다.
         */
    }

    // @Component가 없는 이유는 AnnotationConfigApplicationContext에 직접 넘겨주기에 컴포넌트 스캔의 대상이 된다.
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            // 종료를 해도 destroy() 메서드는 호출되지 않는다.
            System.out.println("prototypeBean.destroy");
        }
    }
}
