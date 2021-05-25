package hello.core2.scope;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototype1 {

    @Test
    @DisplayName("단순 두 프로토타입 빈을 요청한 상황")
    public void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("한 싱글턴 빈을 통해 프로토타입 빈을 요청 - ObjectProvider 사용")
    void singletonClientUsesPrototypeOP() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBeanOP.class, PrototypeBean.class);

        ClientBeanOP clientBeanOP1 = ac.getBean(ClientBeanOP.class);
        int count1 = clientBeanOP1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanOP clientBeanOP2 = ac.getBean(ClientBeanOP.class);
        int count2 = clientBeanOP2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Test
    @DisplayName("한 싱글턴 빈을 통해 프로토타입 빈을 요청 - Provider 사용")
    void singletonClientUsesPrototypeP() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBeanP.class, PrototypeBean.class);

        ClientBeanP clientBeanP1 = ac.getBean(ClientBeanP.class);
        int count1 = clientBeanP1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanP clientBeanP2 = ac.getBean(ClientBeanP.class);
        int count2 = clientBeanP2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBeanOP {

        // ObjectProvider는 <T> 타입을 컨테이너에서 대신 찾아주는 대리자 정도의 역할이라 이해하면 된다.
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            // ObjectProvider에게 <T> 타입의 빈을 getObject 한다.
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();

            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBeanP {

        // javax.inject:javax.inject:1 를 그레이들에 추가해야한다
        // Provider는 <T> 타입을 컨테이너에서 대신 찾아주는 대리자 정도의 역할이라 이해하면 된다.
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            // Provider에게 <T> 타입의 빈을 get 한다.
            PrototypeBean prototypeBean = prototypeBeanProvider.get();

            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
