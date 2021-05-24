package hello.core2.lifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient2 {
    private  String url;

    public NetworkClient2() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect: " + url);
    }

    // 의존관계 주입이 다 끝나고 나면 호출
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init()");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈 종료 직전에 호출
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close()");
        disconnect();
    }
}
