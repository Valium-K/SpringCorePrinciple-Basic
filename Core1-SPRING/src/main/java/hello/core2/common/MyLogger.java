package hello.core2.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// 프록시를 사용하면 다형성을 유지하며 코드를 짤 수 있다.
// @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// 이렇게 하면 컨트롤러나 서비스에서 Provider로 감 쌀 필요없이 싱글톤을 사용하듯 짤 수 있다.
// 일반적으로 프록시를 사용하는 편. 이 코드는 예제를 위해 provider로 짜 보았다.
// 프록시로 변경한다면, 컨트롤러와 서비스단에서 Provider를 풀어주면 된다.
@ComponentScan
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // request 요청 - 응답 사이클 간이 스코프라 보면 된다.
public class MyLogger { // MyLogger는 request 요청 당 하나 씩 생성됨

    private String uuid;    // 각 HTTP request를 구분하기 위한 uuid
    private String requestURL;

    // requestURl은 빈이 생성이 되는 시점에는 알 수 없음으로 setter를 두었다.
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + " [" + requestURL + "] " + message);
    }

    // 생성 후 요청 시 주입
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean has been created: " + this);
    }

    // 소멸시 종료 메시지를 남긴다.
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean has been closed: " + this);
    }
}
