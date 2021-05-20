옵션 처리
========
## 주입할 스프링 빈이 없을 때도 동작을 해야할 때

```java
import java.lang.reflect.Member;
import java.util.Optional;

public class OptionTest {

    @Test
    void option() {
        AnnotationConfigApplicationContext(TestOption.class);
    }

    static class TestOption {

        @Autowired(required = false)
        public void setBean1(Member member1) {
            // 실행을 하지 않음
        }

        @Autowired
        public void setBean2(@Nullable Member member2) {
            // member2 = null
        }

        @Autowired(required = false)
        public void setBean1(Optional<Member> member3) {
            // member3 = Optional.empty
        }


    }
}
```