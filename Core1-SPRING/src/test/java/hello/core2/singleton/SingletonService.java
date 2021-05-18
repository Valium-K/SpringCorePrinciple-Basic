package hello.core2.singleton;

// 싱글톤 생성 예제
public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    private SingletonService() {}

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
