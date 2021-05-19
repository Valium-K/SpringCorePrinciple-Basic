package hello.core2.scan;

import hello.core2.AutoAppConfig;
import hello.core2.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    @DisplayName("컴포넌트 스캔이 잘 되나?")
    void basicScan() {
        // 컴포넌트 스캔 테스트를 위해 @Configuration이 붙은 설정파일을 필터링한다는 설정파일을 사용한다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }
}
