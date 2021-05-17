package hello.core2.beanfind;

import hello.core2.AppConfig;
import hello.core2.member.MemberRepository;
import hello.core2.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameBeanFindTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 있으면 중복 오류가 발생한다.")
    void findBeanDuplicated() {

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 있으면 Bean이름을 지정하면 된다.")
    void findBeanByName() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회")
    void findAllByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {

        // ======= 주의 =======
        // 싱글톤으로 관리된다 해서 new 되는 MemoryMemberRepository 자체, 즉 , 사실상 총 1개의 Bean이 관리가 되는것이 아니라
        // 빈 컨테이너의 key인 memberRepository1, memberRepository2 가 각각 싱글톤으로 관리되는 것 이다.

        // 빈 컨테이너에 key: memberRepository, value: memberRepository.class가 저장됨
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        // 빈 컨테이너에 key: memberRepository2, value: memberRepository.class가 저장됨
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
